package br.com.camiloporto.tenant.model;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity
@RooSerializable
@RooJson
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

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date ultimaAtualizacao;

    @PrePersist
    @PreUpdate
    public void updateUltimaAtualiacao() {
        ultimaAtualizacao = Calendar.getInstance().getTime();
    }
}
