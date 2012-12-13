package br.com.camiloporto.tenant.repository;

import br.com.camiloporto.tenant.model.Usuario;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Usuario.class)
public interface UserRepository {
}
