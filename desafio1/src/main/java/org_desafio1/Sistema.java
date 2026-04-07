package org_desafio1;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Sistema {

    public static void main(String[] args) {
        Fazenda fazenda = new Fazenda();
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            limparTela();
            exibirMenu();
            String opcao = scanner.nextLine().trim();

            try {
                if ("1".equals(opcao)) {
                    incluirAnimal(scanner, fazenda);
                } else if ("2".equals(opcao)) {
                    consultarAnimal(scanner, fazenda);
                } else if ("3".equals(opcao)) {
                    avaliarAnimal(scanner, fazenda);
                } else if ("4".equals(opcao)) {
                    registrarVenda(scanner, fazenda);
                } else if ("5".equals(opcao)) {
                    registrarPerda(scanner, fazenda);
                } else if ("6".equals(opcao)) {
                    relatorioAnimaisPorTipo(scanner, fazenda);
                } else if ("7".equals(opcao)) {
                    relatorioVendas(fazenda);
                } else if ("8".equals(opcao)) {
                    relatorioPerdas(fazenda);
                } else if ("0".equals(opcao)) {
                    executando = false;
                } else {
                    System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

            if (executando) {
                aguardarEnter(scanner);
            }
        }

        scanner.close();
    }

    private static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                try {
                    new ProcessBuilder("clear").inheritIO().start().waitFor();
                } catch (Exception ignored) {
                    // Fallback para terminais com suporte ANSI
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            // Não interrompe o sistema se o terminal não suportar limpeza de tela.
        }
    }

    private static void exibirMenu() {
        System.out.println();
        System.out.println("===== SISTEMA FAZENDA =====");
        System.out.println("1. Incluir animal");
        System.out.println("2. Consultar animal");
        System.out.println("3. Avaliar animal");
        System.out.println("4. Registrar venda");
        System.out.println("5. Registrar perda");
        System.out.println("6. Relatório animais por tipo");
        System.out.println("7. Relatório vendas");
        System.out.println("8. Relatório perdas");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void aguardarEnter(Scanner scanner) {
        System.out.println();
        System.out.print("Pressione ENTER para continuar...");
        scanner.nextLine();
    }

    private static void incluirAnimal(Scanner scanner, Fazenda fazenda) {
        tipoAnimal tipo = lerTipo(scanner);
        double altura = lerDouble(scanner, "Altura: ");
        double comprimento = lerDouble(scanner, "Comprimento: ");
        double largura = lerDouble(scanner, "Largura: ");
        double peso = lerDouble(scanner, "Peso: ");

        Animal animal = new Animal("", tipo, altura, comprimento, largura, peso);
        fazenda.incluirAnimal(animal);
        System.out.println("Animal incluído com ID: " + animal.getId());
    }

    private static void consultarAnimal(Scanner scanner, Fazenda fazenda) {
        System.out.print("ID do animal: ");
        String id = scanner.nextLine().trim();
        Animal animal = fazenda.consultarAnimal(id);

        if (animal == null) {
            System.out.println("Animal não encontrado.");
            return;
        }
        System.out.println(formatarAnimal(animal));
    }

    private static void avaliarAnimal(Scanner scanner, Fazenda fazenda) {
        System.out.print("ID do animal: ");
        String id = scanner.nextLine().trim();
        double altura = lerDouble(scanner, "Nova altura: ");
        double comprimento = lerDouble(scanner, "Novo comprimento: ");
        double largura = lerDouble(scanner, "Nova largura: ");
        double peso = lerDouble(scanner, "Novo peso: ");

        String alerta = fazenda.avaliarAnimal(id, altura, comprimento, largura, peso);
        if (alerta == null) {
            System.out.println("Avaliação atualizada com sucesso.");
        } else {
            System.out.println(alerta);
        }
    }

    private static void registrarVenda(Scanner scanner, Fazenda fazenda) {
        System.out.print("ID do animal: ");
        String id = scanner.nextLine().trim();
        double valor = lerDouble(scanner, "Valor da venda: ");
        fazenda.registrarVenda(id, valor);
        System.out.println("Venda registrada com sucesso.");
    }

    private static void registrarPerda(Scanner scanner, Fazenda fazenda) {
        System.out.print("ID do animal: ");
        String id = scanner.nextLine().trim();
        fazenda.registrarPerda(id);
        System.out.println("Perda registrada com sucesso.");
    }

    private static void relatorioAnimaisPorTipo(Scanner scanner, Fazenda fazenda) {
        tipoAnimal tipo = lerTipo(scanner);
        List<Animal> animais = fazenda.relatorioAnimaisAtivosPorTipo(tipo);
        if (animais.isEmpty()) {
            System.out.println("Nenhum animal ativo encontrado para o tipo informado.");
            return;
        }
        for (Animal animal : animais) {
            System.out.println(formatarAnimal(animal));
        }
    }

    private static void relatorioVendas(Fazenda fazenda) {
        List<Operacao> vendas = fazenda.relatorioVendas();
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }
        for (Operacao venda : vendas) {
            Animal animal = fazenda.consultarAnimal(venda.getIdAnimal());
            if (animal != null) {
                System.out.println(formatarAnimal(animal) + " | valorVenda=" + venda.getValor());
            }
        }
        System.out.println("Somatorio de vendas: " + fazenda.somatorioVendas());
    }

    private static void relatorioPerdas(Fazenda fazenda) {
        List<Animal> perdas = fazenda.relatorioPerdas();
        if (perdas.isEmpty()) {
            System.out.println("Nenhuma perda registrada.");
            return;
        }
        for (Animal animal : perdas) {
            System.out.println(formatarAnimal(animal));
        }
        System.out.println("Perdas por tipo:");
        System.out.println("BOVINO: " + fazenda.contagemPerdasPorTipo(tipoAnimal.BOVINO));
        System.out.println("SUINO: " + fazenda.contagemPerdasPorTipo(tipoAnimal.SUINO));
        System.out.println("EQUINO: " + fazenda.contagemPerdasPorTipo(tipoAnimal.EQUINO));
    }

    private static tipoAnimal lerTipo(Scanner scanner) {
        System.out.print("Tipo (BOVINO, SUINO, EQUINO): ");
        String entrada = scanner.nextLine().trim().toUpperCase();
        try {
            return tipoAnimal.valueOf(entrada);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo inválido. Use BOVINO, SUINO ou EQUINO.");
        }
    }

    private static double lerDouble(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        String valor = scanner.nextLine().trim();
        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Número inválido: " + valor);
        }
    }

    private static String formatarAnimal(Animal animal) {
        return "ID=" + animal.getId()
                + ", tipo=" + animal.getTipo()
                + ", altura=" + animal.getAltura()
                + ", comprimento=" + animal.getComprimento()
                + ", largura=" + animal.getLargura()
                + ", peso=" + animal.getPeso()
                + ", status=" + animal.getStatus();
    }

}