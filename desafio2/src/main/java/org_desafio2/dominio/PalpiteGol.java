package org_desafio2.dominio;

public class PalpiteGol {

    private final Jogador jogador;
    private final int quantidade;

    public PalpiteGol(Jogador jogador, int quantidade) {
        if (jogador == null) {
            throw new IllegalArgumentException("Jogador nao pode ser nulo.");
        }
        this.jogador = jogador;
        this.quantidade = ResultadoJogo.validarGols(quantidade, "Quantidade de gols");
    }

    public Jogador getJogador() {
        return jogador;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return "PalpiteGol{"
                + "jogador=" + jogador.getNome()
                + ", quantidade=" + quantidade
                + '}';
    }
}
