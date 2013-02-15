package br.com.camiloporto.tenant.search;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.testng.annotations.Test;


public class ElasticSearchFactoryBeanTest {

	@Test
	public void deveConfigurarDefaultNode() {
		ElasticSearchNodeFactoryBean factory = new ElasticSearchNodeFactoryBean();
		Node node = factory.node();
		node.start();
		Assert.assertNotNull(node);
		node.stop();
	}
	
	@Test
	public void deveConfigurarNodeViaSettingMap() {
		ElasticSearchNodeFactoryBean factory = new ElasticSearchNodeFactoryBean();
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
		ElasticSearchNodeFactoryBean factory = new ElasticSearchNodeFactoryBean();
		Resource resource = new ClassPathResource("/br/com/camiloporto/tenant/search/elasticsearch.properties");
		factory.setConfigLocation(resource);
		Node node = factory.node();
		node.start();
		Assert.assertEquals(
				"cluster name diferente do esperado",
				"test-cluster",
				node.settings().get("cluster.name"));
		
		node.stop();
	}
	
	@Configuration
	static class ElasticSearchFactoryConfiguration {
		
		@Bean(initMethod = "start", destroyMethod = "close") 
		public Node node() {
			return new ElasticSearchNodeFactoryBean().node();
		}
		
		@Bean
		public Client client() {
			Client client = node().client();
			return client;
		}
		
	}
}
