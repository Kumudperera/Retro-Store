package com.teamx.retroStore.model.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

import java.io.Serializable;


@MappedSuperclass
public abstract class Persistent implements Serializable {

    private static final long serialVersionUID = -3397566548572031856L;
    @Version
    @Column(name = "VERSION", nullable = false)
    protected Long version = null;

    public Persistent() {
        super();
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
