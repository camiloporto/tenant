package br.com.camiloporto.tenant.search;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.network.NetworkUtils;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import br.com.camiloporto.tenant.builder.ImovelBuilder;
import br.com.camiloporto.tenant.model.Imovel;

//@ContextConfiguration(locations = {"/META-INF/spring/applicationContext.xml", "/META-INF/spring/applicationContext-jpa.xml"})
//@ActiveProfiles("unit-test")
public class ElasticSearchConfigTest /*extends AbstractTestNGSpringContextTests */{
	
	private Settings defaultSettings = ImmutableSettings
            .settingsBuilder()
            .put("cluster.name", "test-cluster-" + NetworkUtils.getLocalAddress().getHostName())
            .build();
	private Node node;
	
	@BeforeClass
	public void startNode() {
		node = NodeBuilder.nodeBuilder().local(true).data(true).settings(defaultSettings).build();
		node.start();
		Client client = node.client();
		if(!client.admin().indices()
			.prepareExists("imoveis")
			.execute()
			.actionGet()
			.exists()) {
		client.admin().indices().
			prepareCreate("imoveis").execute().actionGet();
		}
	}
	
	@AfterClass
	public void stopNode() {
		Client client = node.client();
		if(client.admin().indices()
			.prepareExists("imoveis")
			.execute()
			.actionGet()
			.exists()) {

			client.admin().indices().
			prepareDelete("imoveis").execute().actionGet();
		}
		
		node.stop();
	}
	
	@Test
	public void deveIndexarUmImovel() {
		Imovel i = new ImovelBuilder()
			.doTipo("apartamento")
			.noEstado("RN")
			.naCidade("Natal")
			.noBairro("Lagoa Nova")
			.naRua("Tereza Campos")
			.comComplemento("Lifestyle - 302")
			.create();
		i.setId(1L);
		
		ImovelSearchRepository isr = new ImovelElasticSearchRepository(node.client());
		isr.index(i);
		
		Imovel retrieved = isr.findById(1L);
		Assert.assertEquals(
				retrieved.getComplemento(), 
				"Lifestyle - 302", 
				"complemento do imovel diferente do esperado");
		
	}
}
