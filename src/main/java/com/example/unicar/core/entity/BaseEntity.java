package com.example.unicar.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 5883878545428459079L;

    public abstract T getUuid();

    @JsonIgnore
    @Column(updatable = false)
    private Date inclusao;

    @JsonIgnore
    private Date alteracao;

    @JsonIgnore
    private Boolean excluido;

    @PrePersist
    private void setInclusaoNow() {
        setInclusao(new Date());
        setExcluido(false);
    }

    @PreUpdate
    private void setAlteracaoNow() {
        setAlteracao(new Date());
    }

}
