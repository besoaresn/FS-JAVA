# Desafio 2 - Planejamento do Bolao da Copa 2026

## Objetivo

Criar uma aplicacao Java de console, usando Maven, para gerenciar um bolao da Copa de 2026.
O sistema tera dois perfis principais:

- Administrador: cadastra selecoes, jogadores, posicoes, jogos e participantes; depois informa o resultado real dos jogos.
- Participante: registra palpites, consulta sua pontuacao por jogo e visualiza o ranking geral.

## Escopo funcional

### Administrador

- Cadastrar participantes do bolao.
- Cadastrar selecoes.
- Cadastrar jogadores vinculados a uma selecao.
- Informar a posicao de cada jogador.
- Cadastrar jogos entre duas selecoes.
- Encerrar jogo informando:
  - placar real;
  - jogadores que marcaram gols;
  - quantidade de gols de cada jogador.
- Disparar o calculo e armazenamento da pontuacao dos participantes apos o encerramento do jogo.

### Participante

- Listar jogos disponiveis para palpite.
- Registrar palpite antes do jogo ser encerrado:
  - placar previsto;
  - jogadores que farao gols;
  - quantidade de gols prevista para cada jogador.
- Consultar pontuacao obtida em cada jogo.
- Consultar ranking geral do bolao.

## Regras de pontuacao propostas

Como o enunciado permite que o grupo defina as pontuacoes, a proposta inicial sera:

- Acertar a selecao vencedora ou empate: 10 pontos.
- Acertar o numero de gols de uma equipe: 5 pontos por equipe.
- Acertar o placar completo: 20 pontos extras.
- Acertar gols de jogadores: pontos por gol corretamente previsto, variando conforme a posicao:
  - Goleiro: 10 pontos por gol.
  - Zagueiro: 7 pontos por gol.
  - Lateral: 6 pontos por gol.
  - Volante: 5 pontos por gol.
  - Meia: 4 pontos por gol.
  - Atacante: 3 pontos por gol.

Exemplo: se o participante apostou que um zagueiro faria 2 gols, mas ele fez 1, recebe pontos por 1 gol correto.

## Modelo de classes planejado

### Usuarios

- `Usuario` sera uma classe abstrata com dados comuns, como id, nome e email.
- `Administrador` herdara de `Usuario`.
- `Participante` herdara de `Usuario` e tera controle de pontuacao total.

Uso de POO:

- Heranca: `Administrador` e `Participante` estendem `Usuario`.
- Polimorfismo: menus e exibicoes poderao tratar ambos como `Usuario`.

### Copa

- `Selecao`: representa uma selecao nacional.
- `Jogador`: representa um atleta vinculado a uma selecao.
- `Posicao`: enum com as posicoes dos jogadores.
- `Jogo`: representa uma partida entre duas selecoes.
- `ResultadoJogo`: guarda placar real e gols feitos por jogadores.
- `GolJogador`: guarda jogador e quantidade de gols.

Uso de POO:

- Composicao: `Selecao` possui uma lista de `Jogador`.
- Agregacao: `Jogo` referencia duas selecoes ja cadastradas.
- Composicao: `ResultadoJogo` possui uma lista de `GolJogador`.

### Palpites e pontuacao

- `Palpite`: representa a aposta de um participante para um jogo.
- `PalpiteGol`: representa a previsao de gols de um jogador.
- `RegraPontuacao`: interface para regras de calculo.
- Implementacoes previstas:
  - `RegraVencedor`
  - `RegraGolsEquipe`
  - `RegraPlacarExato`
  - `RegraGolsJogador`
- `CalculadoraPontuacao`: aplica todas as regras de pontuacao.

Uso de POO:

- Interface: `RegraPontuacao`.
- Polimorfismo: a calculadora recebe uma lista de regras e executa todas sem conhecer a implementacao concreta.
- Composicao: `Palpite` possui uma lista de `PalpiteGol`.

## Estrutura Maven planejada

```text
desafio2/
  pom.xml
  README.md
  docs/
    planejamento.md
    diagrama-classes.puml
  src/
    main/
      java/
        org_desafio2/
          Sistema.java
          SmokeTest.java
          dominio/
          pontuacao/
          repositorio/
          servico/
```

## Pacotes planejados

- `org_desafio2`: entrada da aplicacao de console.
- `org_desafio2.dominio`: entidades e enums do sistema.
- `org_desafio2.pontuacao`: interface e classes de regras de pontuacao.
- `org_desafio2.repositorio`: armazenamento em memoria com listas e mapas.
- `org_desafio2.servico`: regras de negocio, validacoes e operacoes do bolao.

## Fluxo principal

1. Administrador cadastra selecoes.
2. Administrador cadastra jogadores nas selecoes.
3. Administrador cadastra participantes.
4. Administrador cadastra jogos.
5. Participante registra palpite para um jogo ainda aberto.
6. Administrador informa resultado real do jogo.
7. Sistema calcula e armazena a pontuacao dos palpites daquele jogo.
8. Participante consulta sua pontuacao por jogo.
9. Qualquer usuario consulta o ranking geral.

## Validacoes principais

- Nao permitir jogo com a mesma selecao dos dois lados.
- Nao permitir palpite para jogo encerrado.
- Nao permitir dois palpites do mesmo participante para o mesmo jogo.
- Nao permitir gols negativos.
- Nao permitir jogador de uma selecao diferente das selecoes envolvidas no jogo.
- Nao permitir encerrar jogo sem informar placar valido.

## Plano de implementacao

### Fase 1 - Projeto Maven e dominio

- Criar `pom.xml` com Java 17.
- Criar entidades principais, enums e construtores.
- Criar metodos de consulta e formatacao simples.

### Fase 2 - Servicos e repositorios em memoria

- Criar repositorios baseados em `Map<String, T>`.
- Criar `BolaoService` com cadastros, palpites, resultados e consultas.
- Centralizar validacoes no servico.

### Fase 3 - Pontuacao

- Criar interface `RegraPontuacao`.
- Implementar regras de vencedor, gols por equipe, placar exato e gols por jogador.
- Criar `CalculadoraPontuacao`.
- Persistir a pontuacao calculada dentro do `Palpite`.

### Fase 4 - Console

- Criar `Sistema` com menu inicial:
  - area do administrador;
  - area do participante;
  - ranking;
  - sair.
- Criar fluxos de cadastro e consulta usando `Scanner`.

### Fase 5 - Testes demonstraveis

- Criar `SmokeTest` com um fluxo completo:
  - cadastro de selecoes;
  - cadastro de jogadores;
  - cadastro de participante;
  - cadastro de jogo;
  - registro de palpite;
  - encerramento do jogo;
  - calculo de pontuacao;
  - ranking.
- Usar esse teste como base para o video de resultados.

## Entregaveis

- Codigo fonte Maven em `desafio2`.
- Diagrama de classes em `docs/diagrama-classes.puml`.
- README com comandos para compilar, executar e rodar o smoke test.
- Roteiro sugerido para os videos:
  - video 1: testes e resultados do sistema;
  - video 2: explicacao das classes e dos recursos de POO usados.
