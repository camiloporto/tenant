package br.com.camiloporto.tenant.model;

import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity
@RooSerializable
public class Imovel {

    @NotNull
    private String estado;

    @NotNull
    private String cidade;

    @NotNull
    private String tipo;

    @NotNull
    private String bairro;

    private String rua;

    private String complemento;
}
