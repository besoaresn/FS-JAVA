package org_desafio2.dominio;

public enum Posicao {
    GOLEIRO(10),
    ZAGUEIRO(7),
    LATERAL(6),
    VOLANTE(5),
    MEIA(4),
    ATACANTE(3);

    private final int pontosPorGol;

    Posicao(int pontosPorGol) {
        this.pontosPorGol = pontosPorGol;
    }

    public int getPontosPorGol() {
        return pontosPorGol;
    }
}
