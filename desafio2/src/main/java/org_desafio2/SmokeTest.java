package org_desafio2;

import org_desafio2.dominio.GolJogador;
import org_desafio2.dominio.Jogador;
import org_desafio2.dominio.Jogo;
import org_desafio2.dominio.Palpite;
import org_desafio2.dominio.Participante;
import org_desafio2.dominio.Posicao;
import org_desafio2.dominio.ResultadoJogo;
import org_desafio2.dominio.Selecao;
import org_desafio2.dominio.StatusJogo;
import org_desafio2.servico.BolaoService;

import java.util.List;
import java.util.Map;

public class SmokeTest {

    public static void main(String[] args) {
        BolaoService bolao = new BolaoService();

        Selecao brasil = bolao.cadastrarSelecao("Brasil");
        Selecao argentina = bolao.cadastrarSelecao("Argentina");
        Selecao franca = bolao.cadastrarSelecao("Franca");

        Jogador atacanteBrasil = bolao.cadastrarJogador(brasil.getId(), "Rafael", Posicao.ATACANTE);
        Jogador meiaBrasil = bolao.cadastrarJogador(brasil.getId(), "Bruno", Posicao.MEIA);
        Jogador atacanteArgentina = bolao.cadastrarJogador(argentina.getId(), "Martin", Posicao.ATACANTE);
        Jogador zagueiroFranca = bolao.cadastrarJogador(franca.getId(), "Louis", Posicao.ZAGUEIRO);

        Participante ana = bolao.cadastrarParticipante("Ana", "ana@email.com");
        Participante joao = bolao.cadastrarParticipante("Joao", "joao@email.com");
        Participante maria = bolao.cadastrarParticipante("Maria", "maria@email.com");

        Jogo jogo = bolao.cadastrarJogo(brasil.getId(), argentina.getId());

        Palpite palpiteAna = bolao.registrarPalpite(ana.getId(), jogo.getId(), 2, 1);
        bolao.adicionarGolAoPalpite(palpiteAna.getId(), atacanteBrasil.getId(), 1);
        bolao.adicionarGolAoPalpite(palpiteAna.getId(), meiaBrasil.getId(), 1);
        bolao.adicionarGolAoPalpite(palpiteAna.getId(), atacanteArgentina.getId(), 1);

        Palpite palpiteJoao = bolao.registrarPalpite(joao.getId(), jogo.getId(), 1, 0);
        bolao.adicionarGolAoPalpite(palpiteJoao.getId(), atacanteBrasil.getId(), 1);

        assertTrue(bolao.listarSelecoes().size() == 3, "Cadastro de selecoes");
        assertTrue(bolao.listarJogadores().size() == 4, "Cadastro de jogadores");
        assertTrue(bolao.listarParticipantes().size() == 3, "Cadastro de participantes");
        assertTrue(bolao.listarJogos().size() == 1, "Cadastro de jogo");
        assertTrue(bolao.listarJogosAbertos().size() == 1, "Jogo aberto para palpites");
        assertTrue(bolao.listarPalpitesDoJogo(jogo.getId()).size() == 2, "Palpites registrados para o jogo");
        assertTrue(palpiteAna.getGolsJogadores().size() == 3, "Gols previstos no palpite");
        assertTrue(bolao.buscarSelecao("sel001").getId().equals(brasil.getId()), "Busca de selecao aceita ID minusculo");
        assertTrue(bolao.buscarJogador("jog001").getId().equals(atacanteBrasil.getId()), "Busca de jogador aceita ID minusculo");
        assertTrue(bolao.buscarParticipante("par001").getId().equals(ana.getId()), "Busca de participante aceita ID minusculo");
        assertTrue(bolao.buscarJogo("jogo001").getId().equals(jogo.getId()), "Busca de jogo aceita ID minusculo");

        assertThrows(() -> bolao.registrarPalpite(ana.getId(), jogo.getId(), 0, 0),
                "Bloqueio de palpite duplicado");

        assertThrows(() -> bolao.adicionarGolAoPalpite(palpiteAna.getId(), zagueiroFranca.getId(), 1),
                "Bloqueio de jogador fora do jogo no palpite");
        assertThrows(() -> bolao.adicionarGolAoPalpite(palpiteAna.getId(), atacanteBrasil.getId(), 1),
                "Bloqueio de jogador duplicado no palpite");

        int totalPalpitesAntesDaFalha = bolao.listarPalpites().size();
        assertThrows(() -> bolao.registrarPalpiteCompleto(
                        maria.getId(),
                        jogo.getId(),
                        1,
                        1,
                        Map.of(zagueiroFranca.getId(), 1)
                ),
                "Palpite completo invalido nao deve ser salvo");
        assertEquals(totalPalpitesAntesDaFalha, bolao.listarPalpites().size(),
                "Total de palpites continua igual apos erro no jogador");

        ResultadoJogo resultado = new ResultadoJogo(2, 1);
        resultado.adicionarGolJogador(new GolJogador(atacanteBrasil, 1));
        resultado.adicionarGolJogador(new GolJogador(meiaBrasil, 1));
        resultado.adicionarGolJogador(new GolJogador(atacanteArgentina, 1));

        bolao.encerrarJogo(jogo.getId(), resultado);

        assertTrue(jogo.getStatus() == StatusJogo.ENCERRADO, "Jogo encerrado");
        assertTrue(bolao.listarJogosAbertos().isEmpty(), "Jogo encerrado sai da lista de abertos");
        assertThrows(() -> bolao.adicionarGolAoPalpite(palpiteJoao.getId(), atacanteBrasil.getId(), 1),
                "Bloqueio de alteracao de palpite apos encerramento");

        assertEquals(50, palpiteAna.getPontuacao(), "Pontuacao do palpite da Ana");
        assertEquals(13, palpiteJoao.getPontuacao(), "Pontuacao do palpite do Joao");
        assertEquals(50, ana.getPontuacaoTotal(), "Pontuacao total da Ana");
        assertEquals(13, joao.getPontuacaoTotal(), "Pontuacao total do Joao");

        List<Participante> ranking = bolao.ranking();
        assertTrue(ranking.size() == 3, "Ranking lista participantes");
        assertTrue(ranking.get(0).getId().equals(ana.getId()), "Ranking ordenado por pontuacao");

        System.out.println("Smoke test OK: fluxo principal validado com cadastros, palpites, resultado e pontuacao.");
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException("Falha no smoke test: " + message);
        }
        System.out.println("[OK] " + message);
    }

    private static void assertThrows(Runnable acao, String message) {
        try {
            acao.run();
        } catch (IllegalArgumentException e) {
            System.out.println("[OK] " + message + " (" + e.getMessage() + ")");
            return;
        }
        throw new IllegalStateException("Falha no smoke test: " + message);
    }

    private static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            throw new IllegalStateException(
                    "Falha no smoke test: " + message + " (esperado=" + expected + ", atual=" + actual + ")"
            );
        }
        System.out.println("[OK] " + message + " (esperado=" + expected + ", atual=" + actual + ")");
    }
}
