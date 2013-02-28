package br.com.camiloporto.tenant.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.camiloporto.tenant.model.Imovel;
import br.com.camiloporto.tenant.search.ImovelSearchRepository;

@Component
public class ImovelServiceImpl implements ImovelService {
	
	
	@Autowired
	@Qualifier("imovelElasticSearchRestRepository")
	private ImovelSearchRepository imovelSearchRepository;
	
	@Override
	public List<Imovel> findAllSortedByUltimaAtualizacao() {
		try {
			return imovelSearchRepository.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Collections.EMPTY_LIST;
	}
	
	@Override
	public List<Imovel> genericQuery(String q) {
		try {
			return imovelSearchRepository.genericQuery(q);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Collections.EMPTY_LIST;
	}
	
	@Override
	public Imovel findImovel(String id) {
		try {
			return imovelSearchRepository.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Imovel saveImovel(Imovel imovel){
		try {
			return imovelSearchRepository.index(imovel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
