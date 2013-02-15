package br.com.camiloporto.tenant.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.com.camiloporto.tenant.builder.ImovelBuilder;
import br.com.camiloporto.tenant.model.Imovel;

@ContextConfiguration(locations = {"/META-INF/spring/applicationContext.xml", "/META-INF/spring/applicationContext-jpa.xml"})
@ActiveProfiles("unit-test")
public class ImovelElasticSearchRepositoryTest extends AbstractTestNGSpringContextTests {
	
	
	@Autowired
	private ImovelElasticSearchRepository repository;
	
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
	
}
