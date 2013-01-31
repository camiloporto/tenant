// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.com.camiloporto.tenant.service;

import br.com.camiloporto.tenant.model.Imovel;
import br.com.camiloporto.tenant.repository.ImovelRepository;
import br.com.camiloporto.tenant.service.ImovelServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect ImovelServiceImpl_Roo_Service {
    
    declare @type: ImovelServiceImpl: @Service;
    
    declare @type: ImovelServiceImpl: @Transactional;
    
    @Autowired
    ImovelRepository ImovelServiceImpl.imovelRepository;
    
    public long ImovelServiceImpl.countAllImovels() {
        return imovelRepository.count();
    }
    
    public void ImovelServiceImpl.deleteImovel(Imovel imovel) {
        imovelRepository.delete(imovel);
    }
    
    public Imovel ImovelServiceImpl.findImovel(Long id) {
        return imovelRepository.findOne(id);
    }
    
    public List<Imovel> ImovelServiceImpl.findAllImovels() {
        return imovelRepository.findAll();
    }
    
    public List<Imovel> ImovelServiceImpl.findImovelEntries(int firstResult, int maxResults) {
        return imovelRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }
    
    public void ImovelServiceImpl.saveImovel(Imovel imovel) {
        imovelRepository.save(imovel);
    }
    
    public Imovel ImovelServiceImpl.updateImovel(Imovel imovel) {
        return imovelRepository.save(imovel);
    }
    
}