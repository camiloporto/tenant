package br.com.camiloporto.tenant.search;

import java.util.List;

import br.com.camiloporto.tenant.model.Imovel;

public interface ImovelSearchRepository {

	Imovel index(Imovel i);

	Imovel findById(String id);

	List<Imovel> genericQuery(String string);

}
