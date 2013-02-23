package br.com.camiloporto.tenant.service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
import br.com.camiloporto.tenant.search.ImovelElasticSearchRepository;

@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml",
		"/META-INF/spring/applicationContext-jpa.xml" })
@ActiveProfiles("unit-test")
public class ImovelServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private ImovelService service;
	
	@Autowired
	private Node node;

//	@Autowired
//	private ImovelRepository repository;
	
	@Autowired
	private ImovelElasticSearchRepository repository;

	@BeforeMethod
	public void clearIndexData() {
		QueryBuilder qb = QueryBuilders.matchAllQuery();
		node.client().prepareDeleteByQuery(qb.toString())
				.setQuery(qb.toString()).setIndices("imoveis").execute()
				.actionGet();
	}

	@Test
	public void deveListarTodosOsImoveisEmOrdemDecrescenteDeDataAtualizacao()
			throws InterruptedException {
		final String ruaImovel1 = "Tereza Campos";
		final String ruaImovel2 = "Av Integracao";
		Imovel i1 = new ImovelBuilder().doTipo("Apartamento").noEstado("RN")
				.naCidade("Natal").noBairro("Lagoa Nova").naRua(ruaImovel1)
				.create();
		i1.setUltimaAtualizacao(new GregorianCalendar(2010, Calendar.JANUARY, 10).getTime());

		repository.index(i1);
		Thread.sleep(1000);

		Imovel i2 = new ImovelBuilder().doTipo("Apartamento").noEstado("RN")
				.naCidade("Natal").noBairro("Candelaria").naRua(ruaImovel2)
				.create();
		i2.setUltimaAtualizacao(new GregorianCalendar(2010, Calendar.JANUARY, 11).getTime());

		repository.index(i2);

		List<Imovel> all = service.findAllSortedByUltimaAtualizacao();

		Assert.assertEquals(all.size(), 2,
				"numero de imoveis diferente do esperado");
		Assert.assertEquals(all.get(0).getRua(), ruaImovel2,
				"ordem dos imoveis diferente do esperado");
		Assert.assertEquals(all.get(1).getRua(), ruaImovel1,
				"ordem dos imoveis diferente do esperado");

	}

	@Test
	public void deveRecuperarUmImovelPeloId() {
		Imovel i = new ImovelBuilder().doTipo("Apartamento").noEstado("RN")
				.naCidade("Natal").noBairro("Lagoa Nova")
				.naRua("Tereza Campos").create();
		
		service.saveImovel(i);
		String id = i.getId();

		Imovel saved = service.findImovel(id);
		Assert.assertNotNull(saved, "deveria recuperar imovel salvo");
		Assert.assertEquals(saved.getRua(), "Tereza Campos",
				"rua do imovel diferente do esperado");
	}

	@Test
	public void aoProcurarPorImovelInexistente_DeveRetornarNull() {
		Imovel inexistente = service.findImovel("abcdef");
		Assert.assertNull(inexistente, "imovel deveria ser nulo");
	}

}
