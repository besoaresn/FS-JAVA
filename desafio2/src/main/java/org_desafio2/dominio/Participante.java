package org_desafio2.dominio;

public class Participante extends Usuario {

    private int pontuacaoTotal;

    public Participante(String id, String nome, String email) {
        super(id, nome, email);
    }

    public int getPontuacaoTotal() {
        return pontuacaoTotal;
    }

    public void adicionarPontuacao(int pontos) {
        if (pontos < 0) {
            throw new IllegalArgumentException("Pontuacao nao pode ser negativa.");
        }
        pontuacaoTotal += pontos;
    }

    public void zerarPontuacao() {
        pontuacaoTotal = 0;
    }

    @Override
    public String toString() {
        return "Participante{" + super.toString()
                + ", pontuacaoTotal=" + pontuacaoTotal
                + '}';
    }
}
