package org_desafio2.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultadoJogo {

    private final int golsMandante;
    private final int golsVisitante;
    private final List<GolJogador> golsJogadores = new ArrayList<>();

    public ResultadoJogo(int golsMandante, int golsVisitante) {
        this.golsMandante = validarGols(golsMandante, "Gols do mandante");
        this.golsVisitante = validarGols(golsVisitante, "Gols do visitante");
    }

    public int getGolsMandante() {
        return golsMandante;
    }

    public int getGolsVisitante() {
        return golsVisitante;
    }

    public void adicionarGolJogador(GolJogador golJogador) {
        if (golJogador == null) {
            throw new IllegalArgumentException("Gol do jogador nao pode ser nulo.");
        }
        golsJogadores.add(golJogador);
    }

    public List<GolJogador> getGolsJogadores() {
        return Collections.unmodifiableList(golsJogadores);
    }

    public static int validarGols(int gols, String campo) {
        if (gols < 0) {
            throw new IllegalArgumentException(campo + " nao pode ser negativo.");
        }
        return gols;
    }

    @Override
    public String toString() {
        return "ResultadoJogo{"
                + "golsMandante=" + golsMandante
                + ", golsVisitante=" + golsVisitante
                + ", golsJogadores=" + golsJogadores
                + '}';
    }
}
