package br.com.camiloporto.tenant.model;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity
@RooSerializable
public class Usuario {

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;
    
    private boolean contaConfirmada = false;
}
