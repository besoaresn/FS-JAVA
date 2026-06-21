package org_desafio2;

import org_desafio2.dominio.GolJogador;
import org_desafio2.dominio.Jogador;
import org_desafio2.dominio.Jogo;
import org_desafio2.dominio.Palpite;
import org_desafio2.dominio.Participante;
import org_desafio2.dominio.Posicao;
import org_desafio2.dominio.ResultadoJogo;
import org_desafio2.dominio.Selecao;
import org_desafio2.servico.BolaoService;

import java.util.List;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Sistema {

    private final BolaoService bolao = new BolaoService();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new Sistema().executar();
    }

    private void executar() {
        boolean executando = true;

        while (executando) {
            exibirMenuPrincipal();
            String opcao = scanner.nextLine().trim();

            try {
                if ("1".equals(opcao)) {
                    menuAdministrador();
                } else if ("2".equals(opcao)) {
                    menuParticipante();
                } else if ("3".equals(opcao)) {
                    exibirRanking();
                } else if ("4".equals(opcao)) {
                    carregarDadosExemplo();
                } else if ("0".equals(opcao)) {
                    executando = false;
                } else {
                    System.out.println("Opcao invalida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

            if (executando) {
                aguardarEnter();
            }
        }

        scanner.close();
    }

    private void exibirMenuPrincipal() {
        System.out.println();
        System.out.println("===== BOLAO DA COPA 2026 =====");
        System.out.println("1. Area do administrador");
        System.out.println("2. Area do participante");
        System.out.println("3. Ranking geral");
        System.out.println("4. Carregar dados de exemplo");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opcao: ");
    }

    private void menuAdministrador() {
        boolean voltando = false;

        while (!voltando) {
            System.out.println();
            System.out.println("===== ADMINISTRADOR =====");
            System.out.println("1. Cadastrar selecao");
            System.out.println("2. Cadastrar jogador");
            System.out.println("3. Cadastrar participante");
            System.out.println("4. Cadastrar jogo");
            System.out.println("5. Encerrar jogo e calcular pontuacao");
            System.out.println("6. Listar dados cadastrados");
            System.out.println("7. Carregar dados de exemplo");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opcao: ");

            String opcao = scanner.nextLine().trim();
            try {
                if ("1".equals(opcao)) {
                    cadastrarSelecao();
                } else if ("2".equals(opcao)) {
                    cadastrarJogador();
                } else if ("3".equals(opcao)) {
                    cadastrarParticipante();
                } else if ("4".equals(opcao)) {
                    cadastrarJogo();
                } else if ("5".equals(opcao)) {
                    encerrarJogo();
                } else if ("6".equals(opcao)) {
                    listarDadosCadastrados();
                } else if ("7".equals(opcao)) {
                    carregarDadosExemplo();
                } else if ("0".equals(opcao)) {
                    voltando = true;
                } else {
                    System.out.println("Opcao invalida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

            if (!voltando) {
                aguardarEnter();
            }
        }
    }

    private void menuParticipante() {
        boolean voltando = false;

        while (!voltando) {
            System.out.println();
            System.out.println("===== PARTICIPANTE =====");
            System.out.println("1. Listar jogos abertos");
            System.out.println("2. Registrar palpite");
            System.out.println("3. Consultar minha pontuacao por jogo");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opcao: ");

            String opcao = scanner.nextLine().trim();
            try {
                if ("1".equals(opcao)) {
                    listarJogosAbertos();
                } else if ("2".equals(opcao)) {
                    registrarPalpite();
                } else if ("3".equals(opcao)) {
                    consultarPontuacaoParticipante();
                } else if ("0".equals(opcao)) {
                    voltando = true;
                } else {
                    System.out.println("Opcao invalida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

            if (!voltando) {
                aguardarEnter();
            }
        }
    }

    private void cadastrarSelecao() {
        String nome = lerTexto("Nome da selecao: ");
        Selecao selecao = bolao.cadastrarSelecao(nome);
        System.out.println("Selecao cadastrada: " + formatarSelecao(selecao));
    }

    private void carregarDadosExemplo() {
        bolao.carregarDadosExemplo();
        System.out.println("Dados de exemplo carregados.");
        System.out.println("Use estes IDs para testar rapidamente:");
        listarDadosCadastrados();
    }

    private void cadastrarJogador() {
        listarSelecoes();
        String selecaoId = lerTexto("ID da selecao: ");
        String nome = lerTexto("Nome do jogador: ");
        Posicao posicao = lerPosicao();

        Jogador jogador = bolao.cadastrarJogador(selecaoId, nome, posicao);
        System.out.println("Jogador cadastrado: " + formatarJogador(jogador));
    }

    private void cadastrarParticipante() {
        String nome = lerTexto("Nome do participante: ");
        String email = lerTexto("Email do participante: ");

        Participante participante = bolao.cadastrarParticipante(nome, email);
        System.out.println("Participante cadastrado: " + formatarParticipante(participante));
    }

    private void cadastrarJogo() {
        listarSelecoes();
        String mandanteId = lerTexto("ID da selecao mandante: ");
        String visitanteId = lerTexto("ID da selecao visitante: ");

        Jogo jogo = bolao.cadastrarJogo(mandanteId, visitanteId);
        System.out.println("Jogo cadastrado: " + formatarJogo(jogo));
    }

    private void registrarPalpite() {
        listarParticipantes();
        String participanteId = lerTexto("ID do participante: ");

        listarJogosAbertos();
        String jogoId = lerTexto("ID do jogo: ");
        int golsMandante = lerInteiro("Gols previstos da selecao mandante: ");
        int golsVisitante = lerInteiro("Gols previstos da selecao visitante: ");

        Jogo jogo = bolao.buscarJogo(jogoId);

        System.out.println("Jogadores disponiveis para este jogo:");
        listarJogadoresDoJogo(jogo);
        int quantidadeJogadores = lerInteiro("Quantos jogadores deseja informar como artilheiros no palpite? ");
        Map<String, Integer> golsPorJogador = new LinkedHashMap<>();
        Set<String> jogadoresInformados = new HashSet<>();

        for (int i = 1; i <= quantidadeJogadores; i++) {
            String jogadorId = lerTexto("ID do jogador " + i + ": ");
            String jogadorIdNormalizado = jogadorId.toUpperCase(Locale.ROOT);
            if (!jogadoresInformados.add(jogadorIdNormalizado)) {
                throw new IllegalArgumentException("Jogador ja foi informado neste palpite.");
            }
            int quantidadeGols = lerInteiro("Quantidade de gols previstos para este jogador: ");
            golsPorJogador.put(jogadorId, quantidadeGols);
        }

        Palpite palpite = bolao.registrarPalpiteCompleto(
                participanteId,
                jogoId,
                golsMandante,
                golsVisitante,
                golsPorJogador
        );
        System.out.println("Palpite registrado: " + formatarPalpite(palpite));
    }

    private void encerrarJogo() {
        listarJogosAbertos();
        String jogoId = lerTexto("ID do jogo que sera encerrado: ");
        Jogo jogo = bolao.buscarJogo(jogoId);

        int golsMandante = lerInteiro("Gols reais de " + jogo.getSelecaoMandante().getNome() + ": ");
        int golsVisitante = lerInteiro("Gols reais de " + jogo.getSelecaoVisitante().getNome() + ": ");
        ResultadoJogo resultado = new ResultadoJogo(golsMandante, golsVisitante);

        System.out.println("Jogadores disponiveis para este jogo:");
        listarJogadoresDoJogo(jogo);
        int quantidadeJogadores = lerInteiro("Quantos jogadores marcaram gols? ");

        for (int i = 1; i <= quantidadeJogadores; i++) {
            String jogadorId = lerTexto("ID do jogador que marcou gol " + i + ": ");
            int quantidadeGols = lerInteiro("Quantidade de gols deste jogador: ");
            Jogador jogador = bolao.buscarJogador(jogadorId);
            resultado.adicionarGolJogador(new GolJogador(jogador, quantidadeGols));
        }

        bolao.encerrarJogo(jogoId, resultado);
        System.out.println("Jogo encerrado. Pontuacoes calculadas para os palpites desse jogo.");
        listarPalpitesDoJogo(jogoId);
    }

    private void consultarPontuacaoParticipante() {
        listarParticipantes();
        String participanteId = lerTexto("ID do participante: ");
        Participante participante = bolao.buscarParticipante(participanteId);
        List<Palpite> palpites = bolao.listarPalpitesDoParticipante(participanteId);

        System.out.println();
        System.out.println("Pontuacao de " + participante.getNome() + ": " + participante.getPontuacaoTotal());
        if (palpites.isEmpty()) {
            System.out.println("Nenhum palpite encontrado.");
            return;
        }

        for (Palpite palpite : palpites) {
            System.out.println(formatarPalpite(palpite));
        }
    }

    private void exibirRanking() {
        List<Participante> ranking = bolao.ranking();
        if (ranking.isEmpty()) {
            System.out.println("Nenhum participante cadastrado.");
            return;
        }

        System.out.println();
        System.out.println("===== RANKING =====");
        for (int i = 0; i < ranking.size(); i++) {
            Participante participante = ranking.get(i);
            System.out.println((i + 1) + ". " + participante.getNome()
                    + " (" + participante.getId() + ") - " + participante.getPontuacaoTotal() + " pontos");
        }
    }

    private void listarDadosCadastrados() {
        listarSelecoes();
        listarJogadores();
        listarParticipantes();
        listarJogos();
        listarPalpites();
    }

    private void listarSelecoes() {
        System.out.println();
        System.out.println("Selecoes:");
        List<Selecao> selecoes = bolao.listarSelecoes();
        if (selecoes.isEmpty()) {
            System.out.println("Nenhuma selecao cadastrada.");
            return;
        }
        for (Selecao selecao : selecoes) {
            System.out.println(formatarSelecao(selecao));
        }
    }

    private void listarJogadores() {
        System.out.println();
        System.out.println("Jogadores:");
        List<Jogador> jogadores = bolao.listarJogadores();
        if (jogadores.isEmpty()) {
            System.out.println("Nenhum jogador cadastrado.");
            return;
        }
        for (Jogador jogador : jogadores) {
            System.out.println(formatarJogador(jogador));
        }
    }

    private void listarParticipantes() {
        System.out.println();
        System.out.println("Participantes:");
        List<Participante> participantes = bolao.listarParticipantes();
        if (participantes.isEmpty()) {
            System.out.println("Nenhum participante cadastrado.");
            return;
        }
        for (Participante participante : participantes) {
            System.out.println(formatarParticipante(participante));
        }
    }

    private void listarJogos() {
        System.out.println();
        System.out.println("Jogos:");
        List<Jogo> jogos = bolao.listarJogos();
        if (jogos.isEmpty()) {
            System.out.println("Nenhum jogo cadastrado.");
            return;
        }
        for (Jogo jogo : jogos) {
            System.out.println(formatarJogo(jogo));
        }
    }

    private void listarJogosAbertos() {
        System.out.println();
        System.out.println("Jogos abertos:");
        List<Jogo> jogos = bolao.listarJogosAbertos();
        if (jogos.isEmpty()) {
            System.out.println("Nenhum jogo aberto.");
            return;
        }
        for (Jogo jogo : jogos) {
            System.out.println(formatarJogo(jogo));
        }
    }

    private void listarPalpites() {
        System.out.println();
        System.out.println("Palpites:");
        List<Palpite> palpites = bolao.listarPalpites();
        if (palpites.isEmpty()) {
            System.out.println("Nenhum palpite cadastrado.");
            return;
        }
        for (Palpite palpite : palpites) {
            System.out.println(formatarPalpite(palpite));
        }
    }

    private void listarPalpitesDoJogo(String jogoId) {
        List<Palpite> palpites = bolao.listarPalpitesDoJogo(jogoId);
        if (palpites.isEmpty()) {
            System.out.println("Nenhum palpite para este jogo.");
            return;
        }
        for (Palpite palpite : palpites) {
            System.out.println(formatarPalpite(palpite));
        }
    }

    private void listarJogadoresDoJogo(Jogo jogo) {
        for (Jogador jogador : jogo.getSelecaoMandante().getJogadores()) {
            System.out.println(formatarJogador(jogador));
        }
        for (Jogador jogador : jogo.getSelecaoVisitante().getJogadores()) {
            System.out.println(formatarJogador(jogador));
        }
    }

    private Posicao lerPosicao() {
        System.out.println("Posicoes disponiveis:");
        for (Posicao posicao : Posicao.values()) {
            System.out.println("- " + posicao + " (" + posicao.getPontosPorGol() + " pontos por gol)");
        }

        String entrada = lerTexto("Posicao: ").toUpperCase(Locale.ROOT);
        try {
            return Posicao.valueOf(entrada);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Posicao invalida.");
        }
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        String valor = scanner.nextLine().trim();
        if (valor.isEmpty()) {
            throw new IllegalArgumentException("Valor nao pode ser vazio.");
        }
        return valor;
    }

    private int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        String valor = scanner.nextLine().trim();
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Numero invalido: " + valor);
        }
    }

    private void aguardarEnter() {
        System.out.println();
        System.out.print("Pressione ENTER para continuar...");
        scanner.nextLine();
    }

    private String formatarSelecao(Selecao selecao) {
        return selecao.getId() + " - " + selecao.getNome()
                + " (" + selecao.getJogadores().size() + " jogadores)";
    }

    private String formatarJogador(Jogador jogador) {
        return jogador.getId() + " - " + jogador.getNome()
                + " | " + jogador.getSelecao().getNome()
                + " | " + jogador.getPosicao()
                + " | " + jogador.getPosicao().getPontosPorGol() + " pts/gol";
    }

    private String formatarParticipante(Participante participante) {
        return participante.getId() + " - " + participante.getNome()
                + " | " + participante.getEmail()
                + " | " + participante.getPontuacaoTotal() + " pontos";
    }

    private String formatarJogo(Jogo jogo) {
        String texto = jogo.getId() + " - " + jogo.getSelecaoMandante().getNome()
                + " x " + jogo.getSelecaoVisitante().getNome()
                + " | " + jogo.getStatus();

        if (jogo.getResultado() != null) {
            texto += " | resultado: "
                    + jogo.getResultado().getGolsMandante()
                    + "x"
                    + jogo.getResultado().getGolsVisitante();
        }
        return texto;
    }

    private String formatarPalpite(Palpite palpite) {
        return palpite.getId() + " - " + palpite.getParticipante().getNome()
                + " | " + palpite.getJogo().getSelecaoMandante().getNome()
                + " " + palpite.getGolsMandante()
                + "x"
                + palpite.getGolsVisitante()
                + " " + palpite.getJogo().getSelecaoVisitante().getNome()
                + " | pontuacao=" + palpite.getPontuacao()
                + " | calculado=" + (palpite.isCalculado() ? "sim" : "nao");
    }
}
