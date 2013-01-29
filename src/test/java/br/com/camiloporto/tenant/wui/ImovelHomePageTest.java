package br.com.camiloporto.tenant.wui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import br.com.camiloporto.tenant.model.Imovel;
import br.com.camiloporto.tenant.repository.ImovelBuilder;
import br.com.camiloporto.tenant.service.ImovelService;

@ContextConfiguration(locations = { 
		"/META-INF/spring/applicationContext.xml",
		"/META-INF/spring/applicationContext-jpa.xml"})
@ActiveProfiles("integration-test")
public class ImovelHomePageTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private ImovelService imovelService;
	
	@BeforeClass
	public void setUpMockMvc() {
		Imovel i = new ImovelBuilder()
			.doTipo("Apartamento")
			.naCidade("Natal")
			.noEstado("RN")
			.noBairro("Lagoa Nova")
			.naRua("Tereza Campos")
			.create();
			imovelService.saveImovel(i);
	}
	
	@Test
	public void f() {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.google.com");
	}
}
