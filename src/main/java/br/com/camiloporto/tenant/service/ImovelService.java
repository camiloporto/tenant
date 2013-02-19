package br.com.camiloporto.tenant.service;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import br.com.camiloporto.tenant.model.Imovel;

@RooService(domainTypes = { br.com.camiloporto.tenant.model.Imovel.class })
public interface ImovelService {

	List<Imovel> findAllSortedByUltimaAtualizacao();

	List<Imovel> genericQuery(String q);
}
