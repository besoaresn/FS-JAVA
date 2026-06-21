package org_desafio2.servico;

import org_desafio2.dominio.GolJogador;
import org_desafio2.dominio.Jogador;
import org_desafio2.dominio.Jogo;
import org_desafio2.dominio.Palpite;
import org_desafio2.dominio.PalpiteGol;
import org_desafio2.dominio.Participante;
import org_desafio2.dominio.Posicao;
import org_desafio2.dominio.ResultadoJogo;
import org_desafio2.dominio.Selecao;
import org_desafio2.pontuacao.CalculadoraPontuacao;
import org_desafio2.repositorio.RepositorioMemoria;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BolaoService {

    private final RepositorioMemoria<Selecao> selecoes = new RepositorioMemoria<>();
    private final RepositorioMemoria<Jogador> jogadores = new RepositorioMemoria<>();
    private final RepositorioMemoria<Participante> participantes = new RepositorioMemoria<>();
    private final RepositorioMemoria<Jogo> jogos = new RepositorioMemoria<>();
    private final RepositorioMemoria<Palpite> palpites = new RepositorioMemoria<>();
    private final CalculadoraPontuacao calculadoraPontuacao = new CalculadoraPontuacao();

    private int proximoIdSelecao = 1;
    private int proximoIdJogador = 1;
    private int proximoIdParticipante = 1;
    private int proximoIdJogo = 1;
    private int proximoIdPalpite = 1;

    public Selecao cadastrarSelecao(String nome) {
        Selecao selecao = new Selecao(gerarId("SEL", proximoIdSelecao++), nome);
        selecoes.salvar(selecao.getId(), selecao);
        return selecao;
    }

    public void carregarDadosExemplo() {
        if (possuiDadosCadastrados()) {
            throw new IllegalStateException("Ja existem dados cadastrados. Reinicie o sistema para carregar exemplos limpos.");
        }

        Selecao brasil = cadastrarSelecao("Brasil");
        Selecao argentina = cadastrarSelecao("Argentina");
        Selecao franca = cadastrarSelecao("Franca");

        cadastrarJogador(brasil.getId(), "Rafael", Posicao.ATACANTE);
        cadastrarJogador(brasil.getId(), "Bruno", Posicao.MEIA);
        cadastrarJogador(brasil.getId(), "Caio", Posicao.ZAGUEIRO);
        cadastrarJogador(argentina.getId(), "Martin", Posicao.ATACANTE);
        cadastrarJogador(argentina.getId(), "Nicolas", Posicao.MEIA);
        cadastrarJogador(franca.getId(), "Louis", Posicao.ZAGUEIRO);

        cadastrarParticipante("Ana", "ana@email.com");
        cadastrarParticipante("Joao", "joao@email.com");
        cadastrarParticipante("Maria", "maria@email.com");

        cadastrarJogo(brasil.getId(), argentina.getId());
        cadastrarJogo(franca.getId(), brasil.getId());
    }

    public boolean possuiDadosCadastrados() {
        return selecoes.tamanho() > 0
                || jogadores.tamanho() > 0
                || participantes.tamanho() > 0
                || jogos.tamanho() > 0
                || palpites.tamanho() > 0;
    }

    public Jogador cadastrarJogador(String selecaoId, String nome, Posicao posicao) {
        Selecao selecao = selecoes.obterPorId(selecaoId, "Selecao");
        Jogador jogador = new Jogador(gerarId("JOG", proximoIdJogador++), nome, posicao, selecao);

        selecao.adicionarJogador(jogador);
        jogadores.salvar(jogador.getId(), jogador);
        return jogador;
    }

    public Participante cadastrarParticipante(String nome, String email) {
        Participante participante = new Participante(gerarId("PAR", proximoIdParticipante++), nome, email);
        participantes.salvar(participante.getId(), participante);
        return participante;
    }

    public Jogo cadastrarJogo(String selecaoMandanteId, String selecaoVisitanteId) {
        Selecao mandante = selecoes.obterPorId(selecaoMandanteId, "Selecao mandante");
        Selecao visitante = selecoes.obterPorId(selecaoVisitanteId, "Selecao visitante");
        Jogo jogo = new Jogo(gerarId("JOGO", proximoIdJogo++), mandante, visitante);

        jogos.salvar(jogo.getId(), jogo);
        return jogo;
    }

    public Palpite registrarPalpite(String participanteId, String jogoId, int golsMandante, int golsVisitante) {
        Participante participante = participantes.obterPorId(participanteId, "Participante");
        Jogo jogo = jogos.obterPorId(jogoId, "Jogo");

        validarJogoAberto(jogo);
        validarPalpiteUnico(participante, jogo);

        Palpite palpite = new Palpite(
                gerarId("PAL", proximoIdPalpite++),
                participante,
                jogo,
                golsMandante,
                golsVisitante
        );
        palpites.salvar(palpite.getId(), palpite);
        return palpite;
    }

    public Palpite registrarPalpiteCompleto(
            String participanteId,
            String jogoId,
            int golsMandante,
            int golsVisitante,
            Map<String, Integer> golsPorJogador
    ) {
        Participante participante = participantes.obterPorId(participanteId, "Participante");
        Jogo jogo = jogos.obterPorId(jogoId, "Jogo");

        validarJogoAberto(jogo);
        validarPalpiteUnico(participante, jogo);

        List<PalpiteGol> golsValidados = validarGolsDoPalpite(jogo, golsPorJogador);
        Palpite palpite = new Palpite(
                gerarId("PAL", proximoIdPalpite++),
                participante,
                jogo,
                golsMandante,
                golsVisitante
        );

        for (PalpiteGol palpiteGol : golsValidados) {
            palpite.adicionarPalpiteGol(palpiteGol);
        }

        palpites.salvar(palpite.getId(), palpite);
        return palpite;
    }

    public void adicionarGolAoPalpite(String palpiteId, String jogadorId, int quantidade) {
        Palpite palpite = palpites.obterPorId(palpiteId, "Palpite");
        Jogador jogador = jogadores.obterPorId(jogadorId, "Jogador");

        validarJogoAberto(palpite.getJogo());
        validarJogadorDoJogo(palpite.getJogo(), jogador);
        validarJogadorSemPalpiteDuplicado(palpite, jogador);
        palpite.adicionarPalpiteGol(new PalpiteGol(jogador, quantidade));
    }

    public void encerrarJogo(String jogoId, ResultadoJogo resultado) {
        Jogo jogo = jogos.obterPorId(jogoId, "Jogo");
        validarJogoAberto(jogo);
        validarResultadoDoJogo(jogo, resultado);
        jogo.encerrar(resultado);
        calcularPontuacaoDoJogo(jogo);
    }

    public List<Selecao> listarSelecoes() {
        return selecoes.listar();
    }

    public List<Jogador> listarJogadores() {
        return jogadores.listar();
    }

    public List<Participante> listarParticipantes() {
        return participantes.listar();
    }

    public List<Jogo> listarJogos() {
        return jogos.listar();
    }

    public List<Jogo> listarJogosAbertos() {
        return jogos.listar().stream()
                .filter(Jogo::estaAberto)
                .collect(Collectors.toList());
    }

    public List<Palpite> listarPalpites() {
        return palpites.listar();
    }

    public List<Palpite> listarPalpitesDoParticipante(String participanteId) {
        Participante participante = participantes.obterPorId(participanteId, "Participante");
        return palpites.listar().stream()
                .filter(palpite -> palpite.getParticipante().getId().equals(participante.getId()))
                .collect(Collectors.toList());
    }

    public List<Palpite> listarPalpitesDoJogo(String jogoId) {
        Jogo jogo = jogos.obterPorId(jogoId, "Jogo");
        return palpites.listar().stream()
                .filter(palpite -> palpite.getJogo().getId().equals(jogo.getId()))
                .collect(Collectors.toList());
    }

    public List<Participante> ranking() {
        return participantes.listar().stream()
                .sorted(Comparator.comparingInt(Participante::getPontuacaoTotal)
                        .reversed()
                        .thenComparing(Participante::getNome))
                .collect(Collectors.toList());
    }

    public Selecao buscarSelecao(String id) {
        return selecoes.obterPorId(id, "Selecao");
    }

    public Jogador buscarJogador(String id) {
        return jogadores.obterPorId(id, "Jogador");
    }

    public Participante buscarParticipante(String id) {
        return participantes.obterPorId(id, "Participante");
    }

    public Jogo buscarJogo(String id) {
        return jogos.obterPorId(id, "Jogo");
    }

    public Palpite buscarPalpite(String id) {
        return palpites.obterPorId(id, "Palpite");
    }

    private void validarPalpiteUnico(Participante participante, Jogo jogo) {
        boolean jaExiste = palpites.listar().stream()
                .anyMatch(palpite -> palpite.getParticipante().getId().equals(participante.getId())
                        && palpite.getJogo().getId().equals(jogo.getId()));

        if (jaExiste) {
            throw new IllegalArgumentException("Participante ja registrou palpite para este jogo.");
        }
    }

    private void validarJogoAberto(Jogo jogo) {
        if (!jogo.estaAberto()) {
            throw new IllegalArgumentException("Jogo ja foi encerrado.");
        }
    }

    private void validarJogadorDoJogo(Jogo jogo, Jogador jogador) {
        if (!jogo.envolveSelecao(jogador.getSelecao().getId())) {
            throw new IllegalArgumentException("Jogador nao pertence a uma das selecoes do jogo.");
        }
    }

    private void validarJogadorSemPalpiteDuplicado(Palpite palpite, Jogador jogador) {
        boolean jaExiste = palpite.getGolsJogadores().stream()
                .anyMatch(palpiteGol -> palpiteGol.getJogador().getId().equals(jogador.getId()));

        if (jaExiste) {
            throw new IllegalArgumentException("Jogador ja foi informado neste palpite.");
        }
    }

    private List<PalpiteGol> validarGolsDoPalpite(Jogo jogo, Map<String, Integer> golsPorJogador) {
        List<PalpiteGol> golsValidados = new ArrayList<>();
        Set<String> jogadoresInformados = new HashSet<>();

        if (golsPorJogador == null || golsPorJogador.isEmpty()) {
            return golsValidados;
        }

        for (Map.Entry<String, Integer> entrada : golsPorJogador.entrySet()) {
            Jogador jogador = jogadores.obterPorId(entrada.getKey(), "Jogador");
            validarJogadorDoJogo(jogo, jogador);

            if (!jogadoresInformados.add(jogador.getId())) {
                throw new IllegalArgumentException("Jogador ja foi informado neste palpite.");
            }

            golsValidados.add(new PalpiteGol(jogador, entrada.getValue()));
        }

        return golsValidados;
    }

    private void validarResultadoDoJogo(Jogo jogo, ResultadoJogo resultado) {
        if (resultado == null) {
            throw new IllegalArgumentException("Resultado nao pode ser nulo.");
        }

        int golsMandanteInformados = 0;
        int golsVisitanteInformados = 0;

        for (GolJogador golJogador : resultado.getGolsJogadores()) {
            Jogador jogador = golJogador.getJogador();
            validarJogadorDoJogo(jogo, jogador);

            if (jogador.getSelecao().getId().equals(jogo.getSelecaoMandante().getId())) {
                golsMandanteInformados += golJogador.getQuantidade();
            } else {
                golsVisitanteInformados += golJogador.getQuantidade();
            }
        }

        if (golsMandanteInformados != resultado.getGolsMandante()
                || golsVisitanteInformados != resultado.getGolsVisitante()) {
            throw new IllegalArgumentException("Gols dos jogadores precisam bater com o placar informado.");
        }
    }

    private String gerarId(String prefixo, int numero) {
        return prefixo + String.format("%03d", numero);
    }

    private void calcularPontuacaoDoJogo(Jogo jogo) {
        for (Palpite palpite : listarPalpitesDoJogo(jogo.getId())) {
            int pontos = calculadoraPontuacao.calcular(palpite);
            palpite.registrarPontuacao(pontos);
            palpite.getParticipante().adicionarPontuacao(pontos);
        }
    }
}
