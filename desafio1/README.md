# Desafio 1 - Sistema de Fazenda

Aplicacao Java de console para controle de animais (bovinos, suinos e equinos).

## Funcionalidades

- Incluir animal com validacao de medidas (> 0) e ID unico.
- Consultar animal por ID.
- Avaliar animal com alerta quando alguma medida nova for menor que a anterior.
- Registrar venda (altera status para `VENDIDO` e guarda valor).
- Registrar perda (altera status para `PERDIDO`).
- Relatorio de animais ativos por tipo.
- Relatorio de vendas com somatorio final.
- Relatorio de perdas com contagem por tipo.

## Estrutura

Os fontes estao em `desafio/` (pacote `desafio`).

## Executar via terminal (sem Maven)

```bash
cd /home/onealhtml/IdeaProjects/FS-JAVA/desafio1
javac desafio/*.java
java desafio.Sistema
```

## Smoke test rapido

```bash
cd /home/onealhtml/IdeaProjects/FS-JAVA/desafio1
javac desafio/*.java
java desafio.SmokeTest
```

## Observacao

Se voce preferir usar Maven, pode ajustar o projeto para o layout padrao (`src/main/java`) e executar com `mvn`.
