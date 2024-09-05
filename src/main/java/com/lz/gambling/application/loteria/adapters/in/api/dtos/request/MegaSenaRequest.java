package com.lz.gambling.application.loteria.adapters.in.api.dtos.request;

import com.lz.gambling.domain.loteriascaixa.model.MegaSena;

public record MegaSenaRequest (Long id, String concurso) {

    public String toString() {
        return "MegaSenaRequest(id=" + this.id + ", concurso=" + this.concurso + ")";
    }

    public MegaSena toDomain() {
        return new MegaSena(this.id, this.concurso);
    }
}
