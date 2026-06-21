package org_desafio2.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Palpite {

    private final String id;
    private final Participante participante;
    private final Jogo jogo;
    private final int golsMandante;
    private final int golsVisitante;
    private final List<PalpiteGol> golsJogadores = new ArrayList<>();
    private int pontuacao;
    private boolean calculado;

    public Palpite(String id, Participante participante, Jogo jogo, int golsMandante, int golsVisitante) {
        this.id = Usuario.validarTexto(id, "ID");
        this.participante = validarParticipante(participante);
        this.jogo = validarJogo(jogo);
        this.golsMandante = ResultadoJogo.validarGols(golsMandante, "Gols do mandante");
        this.golsVisitante = ResultadoJogo.validarGols(golsVisitante, "Gols do visitante");
    }

    public String getId() {
        return id;
    }

    public Participante getParticipante() {
        return participante;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public int getGolsMandante() {
        return golsMandante;
    }

    public int getGolsVisitante() {
        return golsVisitante;
    }

    public void adicionarPalpiteGol(PalpiteGol palpiteGol) {
        if (palpiteGol == null) {
            throw new IllegalArgumentException("Palpite de gol nao pode ser nulo.");
        }
        golsJogadores.add(palpiteGol);
    }

    public List<PalpiteGol> getGolsJogadores() {
        return Collections.unmodifiableList(golsJogadores);
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public boolean isCalculado() {
        return calculado;
    }

    public void registrarPontuacao(int pontuacao) {
        if (pontuacao < 0) {
            throw new IllegalArgumentException("Pontuacao nao pode ser negativa.");
        }
        this.pontuacao = pontuacao;
        this.calculado = true;
    }

    private Participante validarParticipante(Participante participante) {
        if (participante == null) {
            throw new IllegalArgumentException("Participante nao pode ser nulo.");
        }
        return participante;
    }

    private Jogo validarJogo(Jogo jogo) {
        if (jogo == null) {
            throw new IllegalArgumentException("Jogo nao pode ser nulo.");
        }
        return jogo;
    }

    @Override
    public String toString() {
        return "Palpite{"
                + "id='" + id + '\''
                + ", participante=" + participante.getNome()
                + ", jogo=" + jogo.getId()
                + ", placar=" + golsMandante + "x" + golsVisitante
                + ", pontuacao=" + pontuacao
                + ", calculado=" + calculado
                + '}';
    }
}
