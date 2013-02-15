package br.com.camiloporto.tenant.search;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.elasticsearch.node.Node;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.testng.annotations.Test;

public class ElasticSearchFactoryBeanTest {

	@Test
	public void deveConfigurarDefaultNode() {
		ElasticSearchFactoryBean factory = new ElasticSearchFactoryBean();
		Node node = factory.node();
		node.start();
		Assert.assertNotNull(node);
		node.stop();
	}
	
	@Test
	public void deveConfigurarNodeViaSettingMap() {
		ElasticSearchFactoryBean factory = new ElasticSearchFactoryBean();
		Map<String, String> settings = new HashMap<String, String>();
		settings.put("cluster.name", "test-cluster");
		settings.put("local", "true");
		
		factory.setSettings(settings);
		Node node = factory.node();
		node.start();
		Assert.assertEquals(
				"cluster name diferente do esperado",
				"test-cluster",
				node.settings().get("cluster.name"));
		
		node.stop();
	}
	
	@Test
	public void deveConfigurarNodeViaProperties() {
		ElasticSearchFactoryBean factory = new ElasticSearchFactoryBean();
		Resource resource = new ClassPathResource("/br/com/camiloporto/tenant/search/elasticsearch.properties");
		factory.setSettings(resource);
		Node node = factory.node();
		node.start();
		Assert.assertEquals(
				"cluster name diferente do esperado",
				"test-cluster",
				node.settings().get("cluster.name"));
		
		node.stop();
	}
}
