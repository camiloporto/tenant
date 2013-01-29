package br.com.camiloporto.tenant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.MvcResult;
import org.springframework.test.web.server.request.MockMvcRequestBuilders;
import org.springframework.test.web.server.result.MockMvcResultMatchers;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import br.com.camiloporto.tenant.model.Imovel;
import br.com.camiloporto.tenant.repository.ImovelBuilder;
import br.com.camiloporto.tenant.repository.ImovelRepository;

@ContextConfiguration(locations = { 
		"/META-INF/spring/applicationContext.xml",
		"/META-INF/spring/applicationContext-jpa.xml",
		"/META-INF/spring/webmvc-config.xml"})
@WebAppConfiguration
public class RealEstateControllerTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private ImovelRepository imovelRepository;
	
	@Autowired 
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@BeforeClass
	public void setUpMockMvc() {
		mockMvc = MockMvcBuilders.webApplicationContextSetup(wac).build();
		Imovel i = new ImovelBuilder()
			.doTipo("Apartamento")
			.naCidade("Natal")
			.noEstado("RN")
			.noBairro("Lagoa Nova")
			.naRua("Tereza Campos")
			.create();
			imovelRepository.save(i);
	}
	
	@Test
	public void deveRetornarViewPrincipalDeImoveis() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/realestates"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("imoveis"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/realestate/index.jsp"))
				.andReturn();
		ModelAndView mav = result.getModelAndView();
		List<Imovel> imoveis = (List<Imovel>) mav.getModelMap().get("imoveis");
		
		final int expectedSize = 1;
		final String expectedRua = "Tereza Campos";
		Assert.assertEquals(imoveis.size(), expectedSize, "numero de imoveis diferente do esperado");
		
		Imovel i = imoveis.get(0);
		Assert.assertEquals(i.getRua(), expectedRua, "nome da rua do imovel diferente do esperado");
	}
}
