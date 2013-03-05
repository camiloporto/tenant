package br.com.camiloporto.tenant.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.com.camiloporto.tenant.AbstractElasticSearchAwareTest;
import br.com.camiloporto.tenant.builder.ImovelBuilder;
import br.com.camiloporto.tenant.model.Imovel;
import br.com.camiloporto.tenant.model.ImovelMedia;

public class MediaElasticSearchRestRepositoryTest extends AbstractElasticSearchAwareTest {
	
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
	
	@Test
	public void deveConsultarMediasDeUmImovelExistente() throws Exception {
		
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
		
		
		List<ImovelMedia> result = repository.findByImovelId(indexed.getId());
		
		Assert.assertEquals(result.size(), 1, "numero de medias retornadas diferente do esperado");
		
		ImovelMedia saved = result.get(0);
		Assert.assertEquals(saved.getId(), mediaIndexed.getId(), "media recuperada diferente do esperado");
	}
	
	@Test
	public void deveRetornarListaVaziaSeNaoExistirMediaParaUmImovel() throws Exception {
		
		Imovel i = new ImovelBuilder().doTipo("apartamento").noEstado("RN")
				.naCidade("Natal").noBairro("Lagoa Nova")
				.naRua("Tereza Campos").comComplemento("Lifestyle - 302")
				.create();
		Imovel indexed = imovelRepository.index(i);
		
		List<ImovelMedia> result = repository.findByImovelId(indexed.getId());
		
		Assert.assertEquals(result.size(), 0, "numero de medias retornadas diferente do esperado");
	}
}
