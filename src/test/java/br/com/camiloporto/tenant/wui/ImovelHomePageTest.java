package br.com.camiloporto.tenant.wui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
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
	
	@Test(groups={"selenium"})
	public void deveListarImoveisCadastrados() {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://localhost:8080/Tenant/realestates");
		
		WebElement el = driver.findElement(By.id("divListaImoveis"));
		Assert.assertTrue(el.getText().contains("Tereza Campos"));
	}
}
