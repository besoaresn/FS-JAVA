package meuprograma;

import java.util.Scanner;

public class condicaoidade {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        classificador pessoa = new classificador();

        pessoa.nome = sc.next();
        pessoa.idade = sc.nextInt();

        pessoa.condicao();
    }
}