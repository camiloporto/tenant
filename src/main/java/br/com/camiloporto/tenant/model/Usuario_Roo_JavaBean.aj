// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.com.camiloporto.tenant.model;

import br.com.camiloporto.tenant.model.Usuario;

privileged aspect Usuario_Roo_JavaBean {
    
    public String Usuario.getEmail() {
        return this.email;
    }
    
    public void Usuario.setEmail(String email) {
        this.email = email;
    }
    
    public String Usuario.getPassword() {
        return this.password;
    }
    
    public void Usuario.setPassword(String password) {
        this.password = password;
    }
    
}