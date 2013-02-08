package br.com.camiloporto.tenant.search;

import java.util.List;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.network.NetworkUtils;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
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
		
		ImovelSearchRepository isr = createImovelSearchRepositoryForTest();
		isr.index(i);
		
		Imovel retrieved = isr.findById(1L);
		Assert.assertEquals(
				retrieved.getComplemento(), 
				"Lifestyle - 302", 
				"complemento do imovel diferente do esperado");
		
	}
	
	@Test
	public void deveBuscarImovelPorId() {
		Imovel i = new ImovelBuilder()
			.doTipo("apartamento")
			.noEstado("RN")
			.naCidade("Natal")
			.noBairro("Lagoa Nova")
			.naRua("Tereza Campos")
			.comComplemento("Lifestyle - 302")
			.create();
		i.setId(1L);
		
		ImovelSearchRepository isr = createImovelSearchRepositoryForTest();
		isr.index(i);
		
		Imovel retrieved = isr.findById(1L);
		Assert.assertEquals(
				retrieved.getComplemento(), 
				"Lifestyle - 302", 
				"complemento do imovel diferente do esperado");
		
	}
	
	@Test
	public void deveRetornarNullQuandoNaoEncontrarImovelPorId() {
		final Long idInexistente = 9999L;
		ImovelSearchRepository isr = createImovelSearchRepositoryForTest();
		
		Imovel retrieved = isr.findById(idInexistente);
		Assert.assertNull(
				retrieved, 
				"deveria retornar um objeto null quando nao encontrar imovel por id");
		
	}
	
	@Test
	public void deveFazerFullTextSearchBasicaEmImovel() {
		Imovel i = new ImovelBuilder()
			.doTipo("apartamento")
			.noEstado("RN")
			.naCidade("Natal")
			.noBairro("Lagoa Nova")
			.naRua("Tereza Campos")
			.comComplemento("Lifestyle - 302")
			.create();
		i.setId(1L);
		
		Imovel i2 = new ImovelBuilder()
			.doTipo("casa")
			.noEstado("RN")
			.naCidade("Natal")
			.noBairro("Lagoa Nova")
			.naRua("Potiguares")
			.comComplemento("Residencial Vitoria - 302")
			.create();
		i2.setId(2L);
		
		ImovelSearchRepository isr = createImovelSearchRepositoryForTest();
		isr.index(i);
		isr.index(i2);
		
		List<Imovel> result = isr.genericQuery("apartamento");
		int expectedCount = 1;
		Assert.assertEquals(
				result.size(), 
				expectedCount, 
				"numero de hits diferente do esperado");
		
	}
	
	
	private ImovelSearchRepository createImovelSearchRepositoryForTest() {
		return new ImovelElasticSearchRepository(node.client(), "imoveis", "imovel");
	}
}
