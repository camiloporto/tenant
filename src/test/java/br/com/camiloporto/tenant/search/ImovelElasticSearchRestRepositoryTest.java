package br.com.camiloporto.tenant.search;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.client.config.ClientConstants;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.PutMapping;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import br.com.camiloporto.tenant.builder.ImovelBuilder;
import br.com.camiloporto.tenant.model.Imovel;

public class ImovelElasticSearchRestRepositoryTest {

	private String mappingPath = "src/main/resources/elasticsearch/imoveis/imovel.json";
	
	private String mappingJSON;
	
	private ImovelElasticSearchRestRepository repository;
	
	private JestClient jestClient;

	private void readMappingFile() throws FileNotFoundException {
		File f =  new File(mappingPath);
		Scanner s = new Scanner(f);
		StringBuilder sb = new StringBuilder();
		while(s.hasNext()) {
			sb.append(s.next());
		}
		mappingJSON = sb.toString();
		s.close();
	}
	
	@BeforeClass
	public void initRepository() throws FileNotFoundException {
		readMappingFile();
		repository = new ImovelElasticSearchRestRepository();
		repository.setIndexName("imoveis");
		repository.setTypeName("imovel");

		ClientConfig clientConfig = new ClientConfig();
		LinkedHashSet<String> servers = new LinkedHashSet<String>();
		servers.add("http://localhost:9200");
		clientConfig.getProperties().put(ClientConstants.SERVER_LIST, servers);
		clientConfig.getProperties().put(ClientConstants.IS_MULTI_THREADED,
				true);

		// Construct a new Jest client according to configuration via factory
		JestClientFactory factory = new JestClientFactory();
		factory.setClientConfig(clientConfig);
		jestClient = factory.getObject();
		repository.setJestClient(jestClient);
	}
	
	@BeforeMethod
	public void clearIndexData() throws Exception {
		DeleteIndex di = new DeleteIndex("imoveis");
		CreateIndex ci = new CreateIndex("imoveis");
		jestClient.execute(di);
		jestClient.execute(ci);
		PutMapping putMapping = new PutMapping(
				"imoveis", "imovel",
                mappingJSON);
		jestClient.execute(putMapping);
	}
	
	@AfterClass
	public void closeJestClient() {
		System.out
				.println("ImovelElasticSearchRestRepositoryTest.closeJestClient()");
		jestClient.shutdownClient();
	}

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
