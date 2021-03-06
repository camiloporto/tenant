package br.com.camiloporto.tenant.search;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.com.camiloporto.tenant.AbstractElasticSearchAwareTest;
import br.com.camiloporto.tenant.builder.ImovelBuilder;
import br.com.camiloporto.tenant.model.Imovel;

public class ImovelElasticSearchRestRepositoryTest extends AbstractElasticSearchAwareTest {

	@Autowired
	private ImovelElasticSearchRestRepository repository;
	
	@Test
	public void deveIndexarUmImovel() throws Exception {
		Imovel i = new ImovelBuilder().doTipo("apartamento").noEstado("RN")
				.naCidade("Natal").noBairro("Lagoa Nova")
				.naRua("Tereza Campos").comComplemento("Lifestyle - 302")
				.create();

		Imovel indexed = repository.index(i);
		Assert.assertNotNull(indexed.getId(),
				"deveria ter atribuido um id ao imovel indexado");

		Imovel retrieved = repository.findById(indexed.getId());
		
		Assert.assertEquals(retrieved.getId(), indexed.getId(),
				"id diferente do esperado");
		
		Assert.assertEquals(retrieved.getComplemento(), "Lifestyle - 302",
				"complemento do imovel diferente do esperado");
		

	}
	
	@Test
	public void deveRetornarNullQuandoNaoEncontrarImovelPorId() throws Exception {
		final String idInexistente = "abcdef";
		
		Imovel retrieved = repository.findById(idInexistente);
		Assert.assertNull(
				retrieved, 
				"deveria retornar um objeto null quando nao encontrar imovel por id");
		
	}
	
	@Test
	public void deveFazerFullTextSearchBasicaEmImovel() throws Exception {
		Imovel i = new ImovelBuilder()
			.doTipo("apartamento")
			.noEstado("RN")
			.naCidade("Natal")
			.noBairro("Lagoa Nova")
			.naRua("Tereza Campos")
			.comComplemento("Lifestyle - 302")
			.create();
		
		Imovel i2 = new ImovelBuilder()
			.doTipo("casa")
			.noEstado("RN")
			.naCidade("Natal")
			.noBairro("Lagoa Nova")
			.naRua("Potiguares")
			.comComplemento("Residencial Vitoria - 302")
			.create();
		
		repository.index(i);
		repository.index(i2);
		
		Assert.assertNotNull(i.getId(),
				"deveria ter atribuido um id ao imovel indexado");
		
		Assert.assertNotNull(i2.getId(),
				"deveria ter atribuido um id ao imovel indexado");
		
		List<Imovel> result = repository.genericQuery("apartamento");
		int expectedCount = 1;
		Assert.assertEquals(
				result.size(), 
				expectedCount, 
				"numero de hits diferente do esperado");
		
	}
	
	@Test
	public void deveRecuperarTodosPorOrdemDeUltimaAtualizacao() throws Exception {
		Imovel i = new ImovelBuilder()
			.doTipo("apartamento")
			.noEstado("RN")
			.naCidade("Natal")
			.noBairro("Lagoa Nova")
			.naRua("Tereza Campos")
			.comComplemento("Lifestyle - 302")
			.create();
		i.setUltimaAtualizacao(new GregorianCalendar(2010, Calendar.JANUARY, 10).getTime());
	
		Imovel i2 = new ImovelBuilder()
			.doTipo("casa")
			.noEstado("RN")
			.naCidade("Natal")
			.noBairro("Lagoa Nova")
			.naRua("Potiguares")
			.comComplemento("Residencial Vitoria - 302")
			.create();
		i2.setUltimaAtualizacao(new GregorianCalendar(2010, Calendar.JANUARY, 11).getTime());
		
	
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
	
	@Test
	public void deveSetarIdsNasConsultas() throws Exception {
		Imovel i = new ImovelBuilder()
			.doTipo("apartamento")
			.noEstado("RN")
			.naCidade("Natal")
			.noBairro("Lagoa Nova")
			.naRua("Tereza Campos")
			.comComplemento("Lifestyle - 302")
			.create();
		i.setUltimaAtualizacao(new GregorianCalendar(2010, Calendar.JANUARY, 10).getTime());
	
	
		Imovel indexed = repository.index(i);
		
		List<Imovel> result = repository.findAll();
		Assert.assertEquals(result.get(0).getId(), indexed.getId(), "id diferente do esperado");
		
	}
}
