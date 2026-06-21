package org_desafio2.pontuacao;

import org_desafio2.dominio.Palpite;
import org_desafio2.dominio.ResultadoJogo;

public class RegraPlacarExato implements RegraPontuacao {

    private static final int PONTOS = 20;

    @Override
    public int calcular(Palpite palpite) {
        ResultadoJogo resultado = palpite.getJogo().getResultado();

        if (palpite.getGolsMandante() == resultado.getGolsMandante()
                && palpite.getGolsVisitante() == resultado.getGolsVisitante()) {
            return PONTOS;
        }
        return 0;
    }
}
