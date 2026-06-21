package org_desafio2.pontuacao;

import org_desafio2.dominio.Palpite;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraPontuacao {

    private final List<RegraPontuacao> regras = new ArrayList<>();

    public CalculadoraPontuacao() {
        regras.add(new RegraVencedor());
        regras.add(new RegraGolsEquipe());
        regras.add(new RegraPlacarExato());
        regras.add(new RegraGolsJogador());
    }

    public int calcular(Palpite palpite) {
        if (palpite == null) {
            throw new IllegalArgumentException("Palpite nao pode ser nulo.");
        }
        if (palpite.getJogo().getResultado() == null) {
            throw new IllegalArgumentException("Jogo ainda nao possui resultado.");
        }

        int total = 0;
        for (RegraPontuacao regra : regras) {
            total += regra.calcular(palpite);
        }
        return total;
    }

    public List<RegraPontuacao> getRegras() {
        return List.copyOf(regras);
    }
}
