package br.com.camiloporto.tenant.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import br.com.camiloporto.tenant.model.Imovel;


public class ImovelServiceImpl implements ImovelService {
	
	private final Sort byUltimaAtualizacaoDesc = new Sort(new Sort.Order(Direction.DESC, "ultimaAtualizacao"));
	
	@Override
	public List<Imovel> findAllSortedByUltimaAtualizacao() {
		return imovelRepository.findAll(byUltimaAtualizacaoDesc);
	}
}
