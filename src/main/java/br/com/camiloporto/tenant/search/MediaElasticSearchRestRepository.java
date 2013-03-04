package br.com.camiloporto.tenant.search;

import br.com.camiloporto.tenant.model.ImovelMedia;

public interface MediaElasticSearchRestRepository {

	ImovelMedia index(ImovelMedia media) throws Exception;

}
