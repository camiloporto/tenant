package br.com.camiloporto.tenant.search;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequestBuilder;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.action.admin.indices.settings.UpdateSettingsRequestBuilder;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

public class ElasticSearchNodeFactoryBean implements FactoryBean<Node>,
		InitializingBean, DisposableBean {

	private static final String INDEX_SETTING_FILENAME = "index-settings.json";

	private static final String JSON_FILE_EXTENSION = ".json";

	private final Log logger = LogFactory.getLog(getClass());

	private Node theNode;

	private Map<String, String> settings;

	private Resource configLocation;

	private List<String> mappings = new ArrayList<String>();

	private String mappingRoot = "/es-mapping";

	public void setConfigLocation(Resource configLocation) {
		this.configLocation = configLocation;
	}

	public Node node() {
		NodeBuilder nodeBuilder = NodeBuilder.nodeBuilder();
		loadSettings(nodeBuilder);
		nodeBuilder.local(true);
		theNode = nodeBuilder.build().start();
		return theNode;
	}

	private void loadSettings(NodeBuilder nodeBuilder) {
		if (settings != null) {
			nodeBuilder.getSettings().put(settings);
		}
		if (configLocation != null) {
			logger.info("configurando via " + configLocation.getFilename());
			try {
				nodeBuilder.getSettings().loadFromStream(
						configLocation.getFilename(),
						configLocation.getInputStream());
			} catch (Exception e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	public void setSettings(Map<String, String> settings) {
		this.settings = settings;

	}

	@Override
	public Node getObject() throws Exception {
		return theNode;
	}

	@Override
	public Class<?> getObjectType() {
		return Node.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void destroy() throws Exception {
		theNode.stop().close();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		theNode = node();
		createIndices();
	}


	public void createIndices() throws Exception {
		Map<String, List<String>> indexMappingMap = new HashMap<String, List<String>>();
		for (String mapInfo : mappings) {
			String[] mapInfoSplitted = mapInfo.split("/");
			String indexName = mapInfoSplitted[0];
			String mapName = mapInfoSplitted[1];

			if (!indexMappingMap.containsKey(indexName)) {
				indexMappingMap.put(indexName, new ArrayList<String>());
			}
			indexMappingMap.get(indexName).add(mapName);
		}
		initIndexesAndMappings(indexMappingMap);
	}

	private void initIndexesAndMappings(
			Map<String, List<String>> indexMappingMap) throws Exception {
		for (String indexName : indexMappingMap.keySet()) {
			createIndexOrUpdateSettings(indexName);
			List<String> indexMappings = indexMappingMap.get(indexName);
			for (String mappingName : indexMappings) {
				createOrUpdateMapping(indexName, mappingName);
			}
		}
	}
	
	private boolean existIndex(String indexName) {
		return theNode.client().admin().indices().prepareExists(indexName).execute().actionGet().exists();
	}

	private void createOrUpdateMapping(String indexName, String mappingName) throws Exception {
		String mappingSource = readMappingSettings(indexName, mappingName);
		PutMappingRequestBuilder pmrb = theNode.client().admin().indices()
				.preparePutMapping(indexName)
				.setType(mappingName);
		if(mappingSource != null) {
			pmrb.setSource(mappingSource);
		}
		PutMappingResponse pmr = pmrb.execute().actionGet();
		if (!pmr.acknowledged()) throw new Exception("Could not create mapping ["+mappingName+"] for index ["+indexName+"].");
	}

	private String readMappingSettings(String indexName, String mappingName) {
		return readFileInClassPath(this.mappingRoot + "/" + indexName + "/" + mappingName + JSON_FILE_EXTENSION);
	}

	private void createIndexOrUpdateSettings(String indexName) throws Exception {
		String settingsSource = readIndexSettings(indexName);
		
		if(!existIndex(indexName)) {
			
			CreateIndexRequestBuilder cirb = theNode.client().admin().indices()
					.prepareCreate(indexName);
			if(settingsSource != null) {
				cirb.setSettings(settingsSource);
			}
			CreateIndexResponse cir = cirb.execute().actionGet();
			if (!cir.acknowledged()) throw new Exception("Could not create index ["+indexName+"].");
		} else {
			UpdateSettingsRequestBuilder usrb = theNode.client().admin().indices().prepareUpdateSettings(indexName);
			if(settingsSource == null) {
				return;
			}
			usrb.setSettings(settingsSource);
			usrb.execute().actionGet();
			OpenIndexRequestBuilder oirb = theNode.client().admin().indices().prepareOpen(indexName);
			OpenIndexResponse oir = oirb.execute().actionGet();
			if(!oir.acknowledged()) throw new Exception("could not open index [" + indexName + "]");
		}
	}

	private String readIndexSettings(String indexName) {
		return readFileInClassPath(this.mappingRoot + "/" + indexName + "/" + INDEX_SETTING_FILENAME);
	}

	private String readFileInClassPath(String url) {
		StringBuffer bufferJSON = new StringBuffer();

		try {
			InputStream ips = ElasticSearchNodeFactoryBean.class
					.getResourceAsStream(url);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;

			while ((line = br.readLine()) != null) {
				bufferJSON.append(line);
			}
			br.close();
		} catch (Exception e) {
			return null;
		}

		return bufferJSON.toString();
	}


	public void setMappingRoot(String mappingRoot) {
		this.mappingRoot = mappingRoot;

	}

	public String getMappingRoot() {
		return mappingRoot;
	}

	public List<String> getMappings() {
		return mappings;
	}

	public void setMappings(List<String> mappings) {
		this.mappings = mappings;
	}

}
