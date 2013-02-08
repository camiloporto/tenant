package br.com.camiloporto.tenant.search;

import br.com.camiloporto.tenant.model.Imovel;

public interface ImovelSearchRepository {

	void index(Imovel i);

	Imovel findById(long id);

}
