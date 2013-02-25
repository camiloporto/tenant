package br.com.camiloporto.tenant.search;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

public class ElasticSearchNodeFactoryBean implements FactoryBean<Node>,
		InitializingBean, DisposableBean {

	private final Log logger = LogFactory.getLog(getClass());

	private Node theNode;

	private Map<String, String> settings;

	private Resource configLocation;

	public void setConfigLocation(Resource configLocation) {
		this.configLocation = configLocation;
	}

	public Node node() {
		NodeBuilder nodeBuilder = NodeBuilder.nodeBuilder();
		loadSettings(nodeBuilder);
		theNode = nodeBuilder.node();
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
		theNode.close();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		theNode = node();
	}

}
