package com.lz.gambling.domain.loteriascaixa.model;

public record MegaSena (Long id, String concurso) {

    public static class MegaSenaBuilder {
        private Long id;
        private String concurso;

        MegaSenaBuilder() {
        }

        public MegaSenaBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public MegaSenaBuilder concurso(String concurso) {
            this.concurso = concurso;
            return this;
        }

        public MegaSena build() {
            return new MegaSena(id, concurso);
        }

        public String toString() {
            return "MegaSena.MegaSenaBuilder(id=" + this.id + ", concurso=" + this.concurso + ")";
        }
    }
}
