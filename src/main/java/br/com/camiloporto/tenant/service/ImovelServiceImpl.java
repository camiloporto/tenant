package br.com.camiloporto.tenant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import br.com.camiloporto.tenant.model.Imovel;
import br.com.camiloporto.tenant.search.ImovelElasticSearchRepository;


public class ImovelServiceImpl implements ImovelService {
	
	private final Sort byUltimaAtualizacaoDesc = new Sort(new Sort.Order(Direction.DESC, "ultimaAtualizacao"));
	
	@Autowired
	private ImovelElasticSearchRepository imovelSearchRepository;
	
	@Override
	public List<Imovel> findAllSortedByUltimaAtualizacao() {
//		return imovelRepository.findAll(byUltimaAtualizacaoDesc);
		return imovelSearchRepository.findAll();
	}
	
	@Override
	public List<Imovel> genericQuery(String q) {
		return imovelSearchRepository.genericQuery(q);
		
	}
}
