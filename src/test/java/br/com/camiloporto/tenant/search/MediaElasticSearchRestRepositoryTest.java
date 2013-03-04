package br.com.camiloporto.tenant.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.com.camiloporto.tenant.builder.ImovelBuilder;
import br.com.camiloporto.tenant.model.Imovel;
import br.com.camiloporto.tenant.model.ImovelMedia;

@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml",
		"/META-INF/spring/applicationContext-jpa.xml" })
@ActiveProfiles("unit-test")
public class MediaElasticSearchRestRepositoryTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private ImovelElasticSearchRestRepository imovelRepository;
	
	@Autowired
	private MediaElasticSearchRestRepository repository;
	
	@Test
	public void deveAtribuirIdentificadorAoIndexarMedia() throws Exception {
		
		Imovel i = new ImovelBuilder().doTipo("apartamento").noEstado("RN")
				.naCidade("Natal").noBairro("Lagoa Nova")
				.naRua("Tereza Campos").comComplemento("Lifestyle - 302")
				.create();
		Imovel indexed = imovelRepository.index(i);
		ImovelMedia media = new ImovelMedia();
		media.setFileExtension("jpg");
		media.setFileName("homer");
		media.setImovelId(indexed.getId());
		
		ImovelMedia mediaIndexed = repository.index(media);
		
		Assert.assertNotNull(mediaIndexed.getId(), "deveria ter atribuido um id a media");
		
	}
}
