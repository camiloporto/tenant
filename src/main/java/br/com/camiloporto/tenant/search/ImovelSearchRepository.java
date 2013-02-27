package br.com.camiloporto.tenant.search;

import java.util.List;

import br.com.camiloporto.tenant.model.Imovel;

public interface ImovelSearchRepository {

	Imovel index(Imovel i) throws Exception;

	Imovel findById(String id) throws Exception;

	List<Imovel> genericQuery(String string) throws Exception;
	
	List<Imovel> findAll() throws Exception;

}
