package br.com.camiloporto.tenant.search;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.core.io.Resource;

public class ElasticSearchFactoryBean {

	private Map<String, String> settings;
	private Resource configLocation;
	
	private final Log logger = LogFactory.getLog(getClass());

	public Node node() {
		NodeBuilder nodeBuilder = NodeBuilder.nodeBuilder();
		loadSettings(nodeBuilder);
		nodeBuilder.local(true);
		return nodeBuilder.build();
	}

	private void loadSettings(NodeBuilder nodeBuilder) {
		if(settings != null) {
			nodeBuilder.getSettings().put(settings);
		}
		if(configLocation != null) {
			logger.info("configurando via " + configLocation.getFilename());
			try {
				nodeBuilder.getSettings().loadFromStream(configLocation.getFilename(), configLocation.getInputStream());
			} catch (Exception e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	public void setSettings(Map<String, String> settings) {
		this.settings = settings;
		
	}

	public void setSettings(Resource configLocation) {
		this.configLocation = configLocation;
		
	}

}
