// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.com.camiloporto.tenant.repository;

import br.com.camiloporto.tenant.model.Imovel;
import br.com.camiloporto.tenant.repository.ImovelRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

privileged aspect ImovelRepository_Roo_Jpa_Repository {
    
    declare parents: ImovelRepository extends JpaRepository<Imovel, Long>;
    
    declare parents: ImovelRepository extends JpaSpecificationExecutor<Imovel>;
    
    declare @type: ImovelRepository: @Repository;
    
}
