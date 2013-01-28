package br.com.camiloporto.tenant.controller;

import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.server.request.MockMvcRequestBuilders;
import org.springframework.test.web.server.result.MockMvcResultMatchers;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.testng.annotations.Test;

import br.com.camiloporto.tenant.web.RealEstateController;

@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml","/META-INF/spring/applicationContext-jpa.xml" })
public class RealEstateControllerTest {
	
	@Test
	public void deveRetornarViewPrincipalDeImoveis() throws Exception {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		
		standaloneSetup(new RealEstateController())
			.setViewResolvers(viewResolver)
			.build()
			.perform(MockMvcRequestBuilders.get("/realestates"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("imoveis"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/realestate/index.jsp"));
	}
}
