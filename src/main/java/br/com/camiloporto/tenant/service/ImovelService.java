package br.com.camiloporto.tenant.service;

import java.util.List;

import br.com.camiloporto.tenant.model.Imovel;

//@RooService(domainTypes = { br.com.camiloporto.tenant.model.Imovel.class })
public interface ImovelService {

	List<Imovel> findAllSortedByUltimaAtualizacao();

	List<Imovel> genericQuery(String q);

	Imovel findImovel(String id);

	Imovel saveImovel(Imovel i);
	
}
