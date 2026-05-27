package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Array com 11 plantas
        Planta[] plantas = new Planta[11];
        
        // Adicionando árvores
        plantas[0] = new Arvore("Carvalho", 500, Enum_especies.CARVALHO);
        plantas[1] = new Arvore("Pinheiro", 800, Enum_especies.PINHEIRO);
        plantas[2] = new Arvore("Rosa Branca", 200, Enum_especies.ROSA);
        
        // Adicionando ervas
        plantas[3] = new Erva("Menta", 30, Enum_especies.MENTA);
        plantas[4] = new Erva("Alecrim", 45, Enum_especies.ALECRIM);
        plantas[5] = new Erva("Girassol", 80, Enum_especies.GIRASSOL);
        
        // Adicionando cactos
        plantas[6] = new Cacto("Saguaro", 300, Enum_especies.SAGUARO);
        plantas[7] = new Cacto("Mandacaru", 200, Enum_especies.MANDACARU);
        plantas[8] = new Planta("Margarida", 40, Enum_especies.ROSA);
        plantas[9] = new Planta("Rosa Vermelha", 50, Enum_especies.ROSA);
        plantas[10] = new Cacto("Cacto Pequeno", 50, Enum_especies.SAGUARO);
        
        // Exibir informações e fazer crescer
        System.out.println("=== PLANTAS DO JARDIM ===\n");
        for (Planta p : plantas) {
            p.exibirInfo();
            p.crescer();
            p.obterEnergia();
            System.out.println();
        }
        
        // Pesquisar plantas de uma espécie com validação
        Scanner scanner = new Scanner(System.in);
        Enum_especies especiePesquisa = null;
        boolean especieValida = false;
        
        System.out.println("\n=== BUSCAR PLANTAS POR ESPÉCIE ===");
        System.out.println("Espécies disponíveis: " + obterListaEspecies());
        
        while (!especieValida) {
            System.out.print("\nDigite a espécie desejada: ");
            String entrada = scanner.nextLine().trim().toUpperCase();
            
            try {
                especiePesquisa = Enum_especies.valueOf(entrada);
                especieValida = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Espécie inválida! Tente novamente.");
            }
        }
        
        System.out.println("\n=== PLANTAS DA ESPÉCIE " + especiePesquisa + " ===\n");
        pesquisar(plantas, especiePesquisa);
        
        scanner.close();
    }
    
    static void pesquisar(Planta[] plantas, Enum_especies especie) {
        boolean encontrou = false;
        for (Planta p : plantas) {
            if (p.getEspecie() == especie) {
                p.exibirInfo();
                System.out.println();
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma planta encontrada para a espécie: " + especie);
        }
    }
    
    static String obterListaEspecies() {
        StringBuilder lista = new StringBuilder();
        Enum_especies[] especies = Enum_especies.values();
        for (int i = 0; i < especies.length; i++) {
            lista.append(especies[i]);
            if (i < especies.length - 1) {
                lista.append(", ");
            }
        }
        return lista.toString();
    }
}


