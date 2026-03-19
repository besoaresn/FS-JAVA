package meuprograma;

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
    }
}
