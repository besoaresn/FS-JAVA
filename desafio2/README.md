# Desafio 2 - Bolao da Copa 2026

Aplicacao Java de console para gerenciamento de um bolao da Copa de 2026.

## Estado atual

Fase 4 implementada:

- Projeto Maven criado.
- Classes de dominio criadas.
- Enums de posicao e status de jogo criados.
- Repositorio em memoria criado.
- `BolaoService` criado com cadastros, palpites, consultas e encerramento de jogos.
- Regras de pontuacao criadas usando interface e polimorfismo.
- `CalculadoraPontuacao` criada.
- Encerramento de jogo calcula e armazena a pontuacao dos palpites.
- `SmokeTest` criado para validar o fluxo principal ate a pontuacao.
- Menu de console criado em `Sistema`.

## Estrutura

```text
desafio2/
  pom.xml
  docs/
  src/main/java/org_desafio2/
```

## Compilar

```bash
cd /home/besoares/IdeaProjects/FS-JAVA/desafio2
mvn compile
```

## Executar

```bash
cd /home/besoares/IdeaProjects/FS-JAVA/desafio2
mvn exec:java -Dexec.mainClass=org_desafio2.Sistema
```

Se o Maven nao estiver disponivel, mas houver Java instalado:

```bash
cd /home/besoares/IdeaProjects/FS-JAVA/desafio2
java -cp src/main/java src/main/java/org_desafio2/Sistema.java
```

O menu permite:

- carregar dados de exemplo para testes rapidos;
- cadastrar selecoes;
- cadastrar jogadores;
- cadastrar participantes;
- cadastrar jogos;
- registrar palpites;
- encerrar jogos;
- calcular pontuacao;
- consultar pontuacao por participante;
- consultar ranking geral.

Observacoes:

- Como os dados ficam em memoria, eles sao perdidos ao fechar o programa.
- Para testar sem cadastrar tudo manualmente, use a opcao `4. Carregar dados de exemplo` no menu principal.
- IDs como `SEL001`, `JOG001`, `PAR001` e `JOGO001` podem ser digitados em maiusculo ou minusculo.

## Smoke test rapido

```bash
cd /home/besoares/IdeaProjects/FS-JAVA/desafio2
mvn exec:java -Dexec.mainClass=org_desafio2.SmokeTest
```

Se o Maven nao estiver disponivel, mas houver Java instalado:

```bash
cd /home/besoares/IdeaProjects/FS-JAVA/desafio2
java -cp src/main/java src/main/java/org_desafio2/SmokeTest.java
```

O smoke test cobre:

- cadastro de selecoes;
- cadastro de jogadores;
- cadastro de participantes;
- cadastro de jogo;
- registro de palpites;
- bloqueio de palpite duplicado;
- bloqueio de jogador fora do jogo;
- encerramento de jogo;
- calculo de pontuacao;
- consulta do ranking.
