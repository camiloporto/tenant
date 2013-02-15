package br.com.camiloporto.tenant.search;

import java.util.List;

import br.com.camiloporto.tenant.model.Imovel;

public interface ImovelSearchRepository {

	void index(Imovel i);

	Imovel findById(long id);

	List<Imovel> genericQuery(String string);

}
