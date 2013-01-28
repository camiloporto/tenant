package br.com.camiloporto.tenant.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.com.camiloporto.tenant.model.Imovel;

@ContextConfiguration(locations = {"/META-INF/spring/applicationContext.xml", "/META-INF/spring/applicationContext-jpa.xml"})
public class ImovelRepositoryTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private ImovelRepository repository;
	
	@Test
	public void deveListarTodosOsImoveis() {
		Imovel i = new ImovelBuilder()
			.doTipo("Apartamento")
			.noEstado("RN")
			.naCidade("Natal")
			.noBairro("Lagoa Nova")
			.naRua("Tereza Campos")
			.create();
		
		repository.save(i);
		
		List<Imovel> all = repository.findAll();
		
		Assert.assertEquals(all.size(), 1, "numero de imoveis diferente do esperado");
		System.out.println(all.get(0));
	}
	
	@Test
	public void deveListarTodosOsImoveisEmOrdemDecrescenteDeDataAtualizacao() {
		Imovel i1 = new ImovelBuilder()
			.doTipo("Apartamento")
			.noEstado("RN")
			.naCidade("Natal")
			.noBairro("Lagoa Nova")
			.naRua("Tereza Campos")
			.create();
		
		Imovel i2 = new ImovelBuilder()
			.doTipo("Apartamento")
			.noEstado("RN")
			.naCidade("Natal")
			.noBairro("Candelaria")
			.naRua("Av integracao")
			.create();
	
		repository.save(i1);
		repository.save(i2);
		
		List<Imovel> all = repository.findAll();
		
		Assert.assertEquals(all.size(), 2, "numero de imoveis diferente do esperado");
		Assert.assertEquals(all.get(0).getRua(), "Av Integracao", "ordem dos imoveis diferente do esperado");
		Assert.assertEquals(all.get(1).getRua(), "Tereza Campos", "ordem dos imoveis diferente do esperado");
		
	}
}
