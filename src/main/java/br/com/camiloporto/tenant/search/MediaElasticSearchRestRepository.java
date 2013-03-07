package br.com.camiloporto.tenant.search;

import java.util.List;

import br.com.camiloporto.tenant.model.ImovelMedia;

public interface MediaElasticSearchRestRepository {

	ImovelMedia index(ImovelMedia media) throws Exception;

	List<ImovelMedia> findByImovelId(String idImovel) throws Exception;

}
