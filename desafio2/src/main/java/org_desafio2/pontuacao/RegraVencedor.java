package org_desafio2.pontuacao;

import org_desafio2.dominio.Palpite;
import org_desafio2.dominio.ResultadoJogo;

public class RegraVencedor implements RegraPontuacao {

    private static final int PONTOS = 10;

    @Override
    public int calcular(Palpite palpite) {
        ResultadoJogo resultado = palpite.getJogo().getResultado();

        int vencedorPalpite = compararPlacar(palpite.getGolsMandante(), palpite.getGolsVisitante());
        int vencedorReal = compararPlacar(resultado.getGolsMandante(), resultado.getGolsVisitante());

        if (vencedorPalpite == vencedorReal) {
            return PONTOS;
        }
        return 0;
    }

    private int compararPlacar(int golsMandante, int golsVisitante) {
        return Integer.compare(golsMandante, golsVisitante);
    }
}
