package br.com.camiloporto.tenant.search;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.node.Node;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ElasticSearchFactoryBeanTest {
	
	private void deleteIndex(String index, Node node) {
		IndicesAdminClient indiceClient = node.client().admin().indices();
		if(indiceClient.prepareExists(index).execute().actionGet().exists()) {
			indiceClient.prepareDelete(index).execute().actionGet();
		}
	}
	
	@Test
	public void deveCriarIndicesEMapeamentoAPartirDeArquivosDeConfiguracoesInformado() throws Exception {
		ElasticSearchNodeFactoryBean factory = new ElasticSearchNodeFactoryBean();
		factory.setMappingRoot("/es-mapping");
		factory.getMappings().add("imoveis-test/imovel-map");
		
		String indexName = "imoveis-test";
		String mappingName = "imovel-map";
		
		Node node = factory.node();
		node.start();
		deleteIndex(indexName, node);
		
		factory.createIndices();
		
		ClusterState state = node.client().admin().cluster().prepareState().setFilterIndices(indexName).execute().actionGet().getState();
		IndexMetaData imd = state.getMetaData().index(indexName);
		
		Assert.assertNotNull(imd, "indice '"+indexName+"' deveria ter sido criado");
		
		int expectedNumberOfShards = 1;
		int expectedNumberOfReplicas = 2;
		
		Assert.assertEquals(
				imd.getNumberOfShards(), 
				expectedNumberOfShards,
				"numero de 'shards' diferente do esperado");
		
		Assert.assertEquals(
				imd.getNumberOfReplicas(), 
				expectedNumberOfReplicas,
				"numero de 'replicas' diferente do esperado");
		
		
		MappingMetaData mmd = imd.getMappings().get(mappingName);
		Assert.assertNotNull(mmd, "mapeamento '"+mappingName+"' deveria ter sido criado");
				
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
