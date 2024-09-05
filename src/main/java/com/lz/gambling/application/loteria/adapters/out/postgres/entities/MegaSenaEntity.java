package com.lz.gambling.application.loteria.adapters.out.postgres.entities;

import com.lz.gambling.domain.loteriascaixa.model.MegaSena;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MegaSenaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String concurso;

    public MegaSenaEntity() {
    }

    // add a constructor in MegaSenaEntity with all attributes
    public MegaSenaEntity(Long id, String concurso) {
        this.id = id;
        this.concurso = concurso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConcurso() {
        return concurso;
    }

    public void setConcurso(String concurso) {
        this.concurso = concurso;
    }

    public static MegaSenaEntity fromDomain(MegaSena megaSena) {
        return new MegaSenaEntity(megaSena.id(), megaSena.concurso());
    }

    public MegaSena toDomain() {
        return new MegaSena(getId(), getConcurso());
    }
}
