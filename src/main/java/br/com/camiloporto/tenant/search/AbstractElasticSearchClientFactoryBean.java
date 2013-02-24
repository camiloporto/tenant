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
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequestBuilder;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.action.admin.indices.settings.UpdateSettingsRequestBuilder;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;


public abstract class AbstractElasticSearchClientFactoryBean implements FactoryBean<Client>,	InitializingBean, DisposableBean {
	
	private static final String INDEX_SETTING_FILENAME = "index-settings.json";

	private static final String JSON_FILE_EXTENSION = ".json";

	private final Log logger = LogFactory.getLog(getClass());

	private Map<String, String> settings;

	private List<String> mappings = new ArrayList<String>();

	private String mappingRoot = "/es-mapping";
	
	private Client client;
	
	abstract protected Client buildClient() throws Exception;
	
	public void setMappingRoot(String mappingRoot) {
		this.mappingRoot = mappingRoot;
	}
	
	public void setMappings(List<String> mappings) {
		this.mappings = mappings;
	}
	
	public void setSettings(Map<String, String> settings) {
		this.settings = settings;
	}
	
	@Override
	public void destroy() throws Exception {
		client.close();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.client = buildClient();
		createIndices();
	}

	@Override
	public Client getObject() throws Exception {
		return this.client;
	}

	@Override
	public Class<?> getObjectType() {
		return Client.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	
	private void createIndices() throws Exception {
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
	
	private void createIndexOrUpdateSettings(String indexName) throws Exception {
		String settingsSource = readIndexSettings(indexName);
		
		if(!existIndex(indexName)) {
			
			CreateIndexRequestBuilder cirb = client.admin().indices()
					.prepareCreate(indexName);
			if(settingsSource != null) {
				cirb.setSettings(settingsSource);
			}
			CreateIndexResponse cir = cirb.execute().actionGet();
			if (!cir.acknowledged()) throw new Exception("Could not create index ["+indexName+"].");
		} else {
			UpdateSettingsRequestBuilder usrb = client.admin().indices().prepareUpdateSettings(indexName);
			if(settingsSource == null) {
				return;
			}
			usrb.setSettings(settingsSource);
			usrb.execute().actionGet();
			OpenIndexRequestBuilder oirb = client.admin().indices().prepareOpen(indexName);
			OpenIndexResponse oir = oirb.execute().actionGet();
			if(!oir.acknowledged()) throw new Exception("could not open index [" + indexName + "]");
		}
	}
	
	private void createOrUpdateMapping(String indexName, String mappingName) throws Exception {
		String mappingSource = readMappingSettings(indexName, mappingName);
		PutMappingRequestBuilder pmrb = client.admin().indices()
				.preparePutMapping(indexName)
				.setType(mappingName);
		if(mappingSource != null) {
			pmrb.setSource(mappingSource);
		}
		PutMappingResponse pmr = pmrb.execute().actionGet();
		if (!pmr.acknowledged()) throw new Exception("Could not create mapping ["+mappingName+"] for index ["+indexName+"].");
	}
	
	private boolean existIndex(String indexName) {
		return client.admin().indices().prepareExists(indexName).execute().actionGet().exists();
	}
	
	private String readIndexSettings(String indexName) {
		return readFileInClassPath(this.mappingRoot + "/" + indexName + "/" + INDEX_SETTING_FILENAME);
	}
	
	private String readMappingSettings(String indexName, String mappingName) {
		return readFileInClassPath(this.mappingRoot + "/" + indexName + "/" + mappingName + JSON_FILE_EXTENSION);
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
	

}
