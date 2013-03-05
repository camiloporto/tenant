package br.com.camiloporto.tenant.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import br.com.camiloporto.tenant.builder.ImovelBuilder;
import br.com.camiloporto.tenant.model.Imovel;
import br.com.camiloporto.tenant.model.ImovelMedia;
import br.com.camiloporto.tenant.search.ImovelSearchRepository;
import br.com.camiloporto.tenant.search.MediaElasticSearchRestRepository;

import com.jayway.jsonpath.JsonPath;

@ContextConfiguration(locations = { 
		"/META-INF/spring/applicationContext.xml",
		"/META-INF/spring/applicationContext-jpa.xml",
		"/META-INF/spring/applicationContext-database-config.xml",
		"/META-INF/spring/webmvc-config.xml"})
@WebAppConfiguration
@ActiveProfiles("unit-test")
public class RealEstateControllerTest extends AbstractTestNGSpringContextTests {
	
	
	@Autowired
	private ImovelSearchRepository searchRepository;
	
	@Autowired 
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;

	@Autowired
	private MediaElasticSearchRestRepository mediaRepository;
	
//	@Autowired
//	private Node node;
	
	
	@BeforeClass
	public void setUpMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
	}
	
	@BeforeMethod
	public void clearIndexData() {
		QueryBuilder qb = QueryBuilders.matchAllQuery();
//		node.client().prepareDeleteByQuery(qb.toString())
//				.setQuery(qb.toString()).setIndices("imoveis").execute()
//				.actionGet();
	}
	
	@Test
	public void deveInserirNovoImovel() throws Exception {
		Imovel i = new ImovelBuilder()
			.doTipo("Apartamento")
			.naCidade("Joao Pessoa")
			.noEstado("PB")
			.noBairro("Manaira")
			.naRua("Aluisio Franca")
			.create();
	
		MvcResult result = mockMvc.perform(
			post("/realestates")
				.param("tipo", i.getTipo())
				.param("cidade", i.getCidade())
				.param("estado", i.getEstado())
				.param("bairro", i.getBairro())
				.param("rua", i.getRua())
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.status").value("ok"))
			.andExpect(jsonPath("$.id").exists())
			.andReturn();
		
		String response = result.getResponse().getContentAsString();
		String imovelId = JsonPath.read(response, "$.id");
		
		Imovel indexed = searchRepository.findById(imovelId);
		Assert.assertNotNull(indexed, "imovel deveria ter sido encontrado");
	
	}
	
	@Test
	public void deveRetornarViewPrincipalDeImoveisComListaDeImoveisCadastrados() throws Exception {
		Imovel i = new ImovelBuilder()
			.doTipo("Apartamento")
			.naCidade("Natal")
			.noEstado("RN")
			.noBairro("Lagoa Nova")
			.naRua("Tereza Campos")
			.create();
		searchRepository.index(i);
		
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
		
		Imovel saved = imoveis.get(0);
		Assert.assertEquals(saved.getRua(), expectedRua, "nome da rua do imovel diferente do esperado");
	}
	
	@Test
	public void deveRetornarViewDeBuscaDeImoveisComResultadoDaBusca() throws Exception {
		Imovel i = new ImovelBuilder()
			.doTipo("Apartamento")
			.naCidade("Natal")
			.noEstado("RN")
			.noBairro("Lagoa Nova")
			.naRua("Tereza Campos")
			.create();
		searchRepository.index(i);
		
		Imovel i2 = new ImovelBuilder()
			.doTipo("Apartamento")
			.naCidade("Natal")
			.noEstado("RN")
			.noBairro("Candelaria")
			.naRua("Integracao")
			.create();
		searchRepository.index(i2);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/realestates").param("q", "candelaria"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("imoveis"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/realestate/index.jsp"))
				.andReturn();
		ModelAndView mav = result.getModelAndView();
		List<Imovel> imoveis = (List<Imovel>) mav.getModelMap().get("imoveis");
		
		final int expectedSize = 1;
		final String expectedRua = "Integracao";
		Assert.assertEquals(imoveis.size(), expectedSize, "numero de imoveis diferente do esperado");
		
		Imovel saved = imoveis.get(0);
		Assert.assertEquals(saved.getRua(), expectedRua, "nome da rua do imovel diferente do esperado");
	}
	
	@Test
	public void deveRetornarImovelPeloIdInformado() throws Exception {
		Imovel i = new ImovelBuilder()
			.doTipo("Apartamento")
			.naCidade("Natal")
			.noEstado("RN")
			.noBairro("Lagoa Nova")
			.naRua("Tereza Campos")
			.create();
		searchRepository.index(i);
		
		ImovelMedia media = new ImovelMedia();
		media.setFileExtension("jpg");
		media.setFileName("homer");
		media.setImovelId(i.getId());
		ImovelMedia mediaIndexed = mediaRepository.index(media);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/realestates/" + i.getId()))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("imovel"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("medias"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/realestate/detail.jsp"))
				.andReturn();
		ModelAndView mav = result.getModelAndView();
		Imovel imovel = (Imovel) mav.getModelMap().get("imovel");
		
		final String expectedRua = "Tereza Campos";
		Assert.assertEquals(imovel.getRua(), expectedRua, "nome da rua do imovel diferente do esperado");
		
		List<String> urlsMedias = (List<String>) mav.getModelMap().get("medias");
		Assert.assertEquals(urlsMedias.size(), 1, "numero de medias retornadas diferente do esperado");
		
		String saved = urlsMedias.get(0);
		Assert.assertTrue(saved.contains(mediaIndexed.getId()), "url da media gerada parece nao esta OK: " + saved);
	}
	
	@Test
	public void deveRetornarNullQuandoNaoEncontrarImovelPeloIdInformado() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/realestates/9999"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/realestate/detail.jsp"))
				.andReturn();
		ModelAndView mav = result.getModelAndView();
		Imovel imovel = (Imovel) mav.getModelMap().get("imovel");
		Assert.assertNull(imovel, "imovel deveria ser nulo");
		
	}
}
