// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.com.camiloporto.tenant.service;

import br.com.camiloporto.tenant.model.Imovel;
import br.com.camiloporto.tenant.service.ImovelService;
import java.util.List;

privileged aspect ImovelService_Roo_Service {
    
    public abstract long ImovelService.countAllImovels();    
    public abstract void ImovelService.deleteImovel(Imovel imovel);    
    public abstract Imovel ImovelService.findImovel(Long id);    
    public abstract List<Imovel> ImovelService.findAllImovels();    
    public abstract List<Imovel> ImovelService.findImovelEntries(int firstResult, int maxResults);    
    public abstract void ImovelService.saveImovel(Imovel imovel);    
    public abstract Imovel ImovelService.updateImovel(Imovel imovel);    
}