package br.com.camiloporto.tenant.search;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.client.config.ClientConstants;
import io.searchbox.core.DeleteByQuery;
import io.searchbox.core.Index;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;

import java.util.LinkedHashSet;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import br.com.camiloporto.tenant.builder.ImovelBuilder;
import br.com.camiloporto.tenant.model.Imovel;

public class ImovelElasticSearchRestRepositoryTest {

	private ImovelElasticSearchRestRepository repository;
	
	private JestClient jestClient;

	@BeforeClass
	public void initRepository() {
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
	
	public class DeleteByQueryCustom extends DeleteByQuery {
		public DeleteByQueryCustom(String string) {
			super(string);
		}

		@Override
		public String getRestMethodName() {
			return "DELETE";
		}
	}
}
