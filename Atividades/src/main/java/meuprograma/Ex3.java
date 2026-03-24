package meuprograma;
import java.util.Scanner;

public class Ex3 {
    public static void main(String[] args) {
        
        Conta p1 = new Conta("Lionel Messi", "000.111.222-33");
        p1.realizarDeposito(200);
        p1.realizarSaque(150);
        p1.mostrarDados();

        System.out.println("");

        Conta p2 = new Conta("Neymar", "123.456.789-00");
        p2.realizarDeposito(1000);
        p2.realizarSaque(2000);
        p2.mostrarDados();

        System.out.println("");

        Conta p3 = new Conta("Suárez", "123.321.098-44");
        p3.realizarSaque(100);
        p3.mostrarDados();

        // Array de objetos do tipo conta bancária
        Conta ct[] = new Conta[3];

        Scanner sc = new Scanner(System.in);
        String nome;

        for (int cont = 0; cont <= 2; cont++){

            System.out.print("informe o nome: " );
            nome = sc.next();
            System.out.print("informe o cpf: " );
            String cpf = sc.next();
            ct[cont] = new Conta(nome, cpf);
        }

        ct[0].realizarDeposito(500);
        ct[1].realizarSaque(100);
        ct[2].realizarDeposito(-10);

        for (int cont = 0; cont <= 2; cont++){
            ct[cont].mostrarDados();
        }
    }
}
