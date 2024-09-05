package com.lz.gambling.application.loteria.adapters.in.api.dtos.response;

import com.lz.gambling.domain.loteriascaixa.model.MegaSena;

public record MegaSenaResponse(Long id, String concurso) {

    static final class MegaSenaResponseBuilder {
        private Long id;
        private String concurso;

        MegaSenaResponseBuilder() {
        }

        public static MegaSenaResponseBuilder builder() {
            return new MegaSenaResponseBuilder();
        }

        public MegaSenaResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public MegaSenaResponseBuilder concurso(String concurso) {
            this.concurso = concurso;
            return this;
        }

        public MegaSenaResponse build() {
            return new MegaSenaResponse(id, concurso);
        }

        public String toString() {
            return "MegaSenaResponse.MegaSenaResponseBuilder(id=" + this.id + ", concurso=" + this.concurso + ")";
        }
    }

    public static MegaSenaResponse fromDomain(MegaSena megaSena) {
        return MegaSenaResponseBuilder.builder()
                .id(megaSena.id())
                .concurso(megaSena.concurso())
                .build();
    }
}
