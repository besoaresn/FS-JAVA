package org_desafio2.pontuacao;

import org_desafio2.dominio.GolJogador;
import org_desafio2.dominio.Palpite;
import org_desafio2.dominio.PalpiteGol;

import java.util.HashMap;
import java.util.Map;

public class RegraGolsJogador implements RegraPontuacao {

    @Override
    public int calcular(Palpite palpite) {
        Map<String, Integer> golsReais = golsReaisPorJogador(palpite);
        int pontos = 0;

        for (PalpiteGol palpiteGol : palpite.getGolsJogadores()) {
            String jogadorId = palpiteGol.getJogador().getId();
            int golsAcertados = Math.min(
                    palpiteGol.getQuantidade(),
                    golsReais.getOrDefault(jogadorId, 0)
            );
            pontos += golsAcertados * palpiteGol.getJogador().getPosicao().getPontosPorGol();
        }

        return pontos;
    }

    private Map<String, Integer> golsReaisPorJogador(Palpite palpite) {
        Map<String, Integer> golsReais = new HashMap<>();

        for (GolJogador golJogador : palpite.getJogo().getResultado().getGolsJogadores()) {
            String jogadorId = golJogador.getJogador().getId();
            int quantidadeAtual = golsReais.getOrDefault(jogadorId, 0);
            golsReais.put(jogadorId, quantidadeAtual + golJogador.getQuantidade());
        }

        return golsReais;
    }
}
