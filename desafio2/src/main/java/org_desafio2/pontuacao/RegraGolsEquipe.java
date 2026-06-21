package org_desafio2.pontuacao;

import org_desafio2.dominio.Palpite;
import org_desafio2.dominio.ResultadoJogo;

public class RegraGolsEquipe implements RegraPontuacao {

    private static final int PONTOS_POR_EQUIPE = 5;

    @Override
    public int calcular(Palpite palpite) {
        ResultadoJogo resultado = palpite.getJogo().getResultado();
        int pontos = 0;

        if (palpite.getGolsMandante() == resultado.getGolsMandante()) {
            pontos += PONTOS_POR_EQUIPE;
        }
        if (palpite.getGolsVisitante() == resultado.getGolsVisitante()) {
            pontos += PONTOS_POR_EQUIPE;
        }

        return pontos;
    }
}
