package br.com.camiloporto.tenant.repository;

import br.com.camiloporto.tenant.model.Imovel;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Imovel.class)
public interface ImovelRepository {
}
