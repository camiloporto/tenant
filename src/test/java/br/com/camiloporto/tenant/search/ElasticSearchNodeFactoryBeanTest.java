package br.com.camiloporto.tenant.search;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.client.config.ClientConstants;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.IndicesExists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.elasticsearch.node.Node;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ElasticSearchNodeFactoryBeanTest {
	
	@Test
	public void deveCriarIndicesEMapeamentoAPartirDeArquivosDeConfiguracoesInformado() throws Exception {
		ElasticSearchNodeFactoryBean factory = new ElasticSearchNodeFactoryBean();
		
		Node node = factory.node();
		node.start();
		
				
		node.stop();
	}
	
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
				"test-cluster",
				node.settings().get("cluster.name"),
				"cluster name diferente do esperado");
		
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
				"test-cluster",
				node.settings().get("cluster.name"),
				"cluster name diferente do esperado");
		
		node.stop();
	}
	
}
