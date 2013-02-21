package br.com.camiloporto.tenant.search;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import br.com.camiloporto.tenant.builder.ImovelBuilder;
import br.com.camiloporto.tenant.model.Imovel;

@ContextConfiguration(locations = {"/META-INF/spring/applicationContext.xml", "/META-INF/spring/applicationContext-jpa.xml"})
@ActiveProfiles("unit-test")
public class ImovelElasticSearchRepositoryTest extends AbstractTestNGSpringContextTests {
	
	
	@Autowired
	private ImovelElasticSearchRepository repository;
	
	@Autowired
	private Node node;
	
	@BeforeMethod
	public void clearIndexData() {
		QueryBuilder qb = QueryBuilders.matchAllQuery();
		node.client()
			.prepareDeleteByQuery(qb.toString())
			.setQuery(qb.toString())
			.setIndices("imoveis")
			.execute()
			.actionGet();
	}
	
	@BeforeMethod
	public void printIndexMappings() {
		ClusterState state = node.client().admin().cluster().prepareState().setFilterIndices("imoveis").execute().actionGet().getState();
		IndexMetaData imd = state.getMetaData().index("imoveis");
		for (String m : imd.mappings().keySet()) {
			System.out
					.println("ImovelElasticSearchRepositoryTest.printIndexMappings() " +m);
		}
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
		
		repository.index(i);
		
		Imovel retrieved = repository.findById(1L);
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
		
		repository.index(i);
		
		Imovel retrieved = repository.findById(1L);
		Assert.assertEquals(
				retrieved.getComplemento(), 
				"Lifestyle - 302", 
				"complemento do imovel diferente do esperado");
		
	}
	
	@Test
	public void deveRetornarNullQuandoNaoEncontrarImovelPorId() {
		final Long idInexistente = 9999L;
		
		Imovel retrieved = repository.findById(idInexistente);
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
		
		repository.index(i);
		repository.index(i2);
		
		List<Imovel> result = repository.genericQuery("apartamento");
		int expectedCount = 1;
		Assert.assertEquals(
				result.size(), 
				expectedCount, 
				"numero de hits diferente do esperado");
		
	}
	
	@Test
	public void deveRecuperarTodos() {
		Imovel i = new ImovelBuilder()
			.doTipo("apartamento")
			.noEstado("RN")
			.naCidade("Natal")
			.noBairro("Lagoa Nova")
			.naRua("Tereza Campos")
			.comComplemento("Lifestyle - 302")
			.create();
		i.setUltimaAtualizacao(new GregorianCalendar(2010, Calendar.JANUARY, 10).getTime());
		i.setId(1L);
	
		Imovel i2 = new ImovelBuilder()
			.doTipo("casa")
			.noEstado("RN")
			.naCidade("Natal")
			.noBairro("Lagoa Nova")
			.naRua("Potiguares")
			.comComplemento("Residencial Vitoria - 302")
			.create();
		i2.setUltimaAtualizacao(new GregorianCalendar(2010, Calendar.JANUARY, 11).getTime());
		i2.setId(2L);
		
	
		repository.index(i);
		repository.index(i2);
		
		List<Imovel> result = repository.findAll();
		int expectedCount = 2;
		Assert.assertEquals(
				result.size(), 
				expectedCount, 
				"numero de hits diferente do esperado");
		
		String expectedFirstRua = "Potiguares";
		String expectedSecondRua = "Tereza Campos";
		
		Assert.assertEquals(result.get(0).getRua(), expectedFirstRua, "ordem da pesquisa diferente da esperada");
		Assert.assertEquals(result.get(1).getRua(), expectedSecondRua, "ordem da pesquisa diferente da esperada");
		
	}
	
}
