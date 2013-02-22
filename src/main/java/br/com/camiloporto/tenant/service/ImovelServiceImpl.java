package br.com.camiloporto.tenant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.camiloporto.tenant.model.Imovel;
import br.com.camiloporto.tenant.search.ImovelElasticSearchRepository;

@Component
public class ImovelServiceImpl implements ImovelService {
	
	
	@Autowired
	private ImovelElasticSearchRepository imovelSearchRepository;
	
	@Override
	public List<Imovel> findAllSortedByUltimaAtualizacao() {
		return imovelSearchRepository.findAll();
	}
	
	@Override
	public List<Imovel> genericQuery(String q) {
		return imovelSearchRepository.genericQuery(q);
		
	}
	
	@Override
	public Imovel findImovel(String id) {
		return imovelSearchRepository.findById(id);
	}
	
	public Imovel saveImovel(Imovel imovel){
		return imovelSearchRepository.index(imovel);
	}
}
