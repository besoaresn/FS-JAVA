package org_desafio1;

import java.util.List;

public class SmokeTest {

    public static void main(String[] args) {
        Fazenda fazenda = new Fazenda();

        Animal bovino = new Animal("", tipoAnimal.BOVINO, 1.2, 2.1, 0.8, 320.0);
        Animal suino = new Animal("", tipoAnimal.SUINO, 0.7, 1.1, 0.5, 120.0);
        Animal equino = new Animal("", tipoAnimal.EQUINO, 1.5, 2.4, 0.9, 420.0);

        fazenda.incluirAnimal(bovino);
        fazenda.incluirAnimal(suino);
        fazenda.incluirAnimal(equino);

        assertTrue(bovino.getId() != null && !bovino.getId().isBlank(), "ID do bovino gerado");
        assertTrue(suino.getId() != null && !suino.getId().isBlank(), "ID do suino gerado");
        assertTrue(equino.getId() != null && !equino.getId().isBlank(), "ID do equino gerado");

        assertTrue(fazenda.consultarAnimal(bovino.getId()) != null, "Consulta de animal cadastrado");

        String alerta = fazenda.avaliarAnimal(bovino.getId(), 1.3, 2.2, 0.9, 330.0);
        assertTrue(alerta == null, "Avaliacao com crescimento sem alerta");

        alerta = fazenda.avaliarAnimal(bovino.getId(), 1.0, 2.2, 0.9, 330.0);
        assertTrue(alerta != null && alerta.contains("ALERTA"), "Avaliacao com regressao gera alerta");

        fazenda.registrarVenda(bovino.getId(), 2500.0);
        assertTrue(fazenda.consultarAnimal(bovino.getId()).getStatus() == StatusAnimal.VENDIDO,
                "Status apos venda");

        fazenda.registrarPerda(suino.getId());
        assertTrue(fazenda.consultarAnimal(suino.getId()).getStatus() == StatusAnimal.PERDIDO,
                "Status apos perda");

        List<Animal> ativosEquinos = fazenda.relatorioAnimaisAtivosPorTipo(tipoAnimal.EQUINO);
        assertTrue(ativosEquinos.size() == 1 && ativosEquinos.get(0).getId().equals(equino.getId()),
                "Relatorio ativos por tipo");

        List<Operacao> vendas = fazenda.relatorioVendas();
        assertTrue(vendas.size() == 1, "Relatorio de vendas tem 1 registro");
        assertTrue(vendas.get(0).getTipo() == TipoOperacao.VENDA, "Registro de venda com tipo correto");
        assertDoubleEquals(2500.0, fazenda.somatorioVendas(), 0.0001, "Somatorio de vendas");

        List<Animal> perdas = fazenda.relatorioPerdas();
        assertTrue(perdas.size() == 1 && perdas.get(0).getId().equals(suino.getId()), "Relatorio de perdas");
        assertTrue(fazenda.contagemPerdasPorTipo(tipoAnimal.SUINO) == 1, "Contagem perdas suino");
        assertTrue(fazenda.contagemPerdasPorTipo(tipoAnimal.BOVINO) == 0, "Contagem perdas bovino");
        assertTrue(fazenda.contagemPerdasPorTipo(tipoAnimal.EQUINO) == 0, "Contagem perdas equino");

        System.out.println("Smoke test OK: fluxo principal validado com sucesso.");
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException("Falha no smoke test: " + message);
        }
        System.out.println("[OK] " + message);
    }

    private static void assertDoubleEquals(double expected, double actual, double delta, String message) {
        if (Math.abs(expected - actual) > delta) {
            throw new IllegalStateException(
                    "Falha no smoke test: " + message + " (esperado=" + expected + ", atual=" + actual + ")");
        }
        System.out.println("[OK] " + message + " (esperado=" + expected + ", atual=" + actual + ")");
    }
}


