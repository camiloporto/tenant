package br.com.camiloporto.tenant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import br.com.camiloporto.tenant.model.Imovel;
import br.com.camiloporto.tenant.repository.ImovelBuilder;
import br.com.camiloporto.tenant.repository.ImovelRepository;

@ContextConfiguration(locations = {"/META-INF/spring/applicationContext.xml", "/META-INF/spring/applicationContext-jpa.xml"})
@ActiveProfiles("unit-test")
public class ImovelServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private ImovelService service;
	
	@Autowired
	private ImovelRepository repository;
	
	@BeforeMethod
	public void cleanAllData() {
		repository.deleteAll();
	}

	@Test
	public void deveListarTodosOsImoveisEmOrdemDecrescenteDeDataAtualizacao()
			throws InterruptedException {
		final String ruaImovel1 = "Tereza Campos";
		final String ruaImovel2 = "Av Integracao";
		Imovel i1 = new ImovelBuilder().doTipo("Apartamento").noEstado("RN")
				.naCidade("Natal").noBairro("Lagoa Nova").naRua(ruaImovel1)
				.create();

		repository.save(i1);
		Thread.sleep(1000);

		Imovel i2 = new ImovelBuilder().doTipo("Apartamento").noEstado("RN")
				.naCidade("Natal").noBairro("Candelaria").naRua(ruaImovel2)
				.create();

		repository.save(i2);

		List<Imovel> all = service.findAllSortedByUltimaAtualizacao();

		Assert.assertEquals(all.size(), 2,
				"numero de imoveis diferente do esperado");
		Assert.assertEquals(all.get(0).getRua(), ruaImovel2,
				"ordem dos imoveis diferente do esperado");
		Assert.assertEquals(all.get(1).getRua(), ruaImovel1,
				"ordem dos imoveis diferente do esperado");

	}
}
