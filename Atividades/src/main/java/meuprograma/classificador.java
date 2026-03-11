package meuprograma;

public class classificador {
    String nome;
    int idade;

        public void condicao() {
            if (idade <= 12) {
                System.out.println(nome + " Criança");
            } else if (idade <= 17) {
                System.out.println(nome + " Adolescente");
            } else {
                System.out.println(nome + " Adulto");
            }
        }
    }
