package meuprograma;

public class Ex3 {
    public static void main(String[] args) {
        
        Conta p1 = new Conta("Lionel Messi", "000.111.222-33");
        p1.setDeposito(200);
        p1.setSacar(150);
        p1.getMostrarDados();

        System.out.println("");

        Conta p2 = new Conta("Neymar", "123.456.789-00");
        p2.setDeposito(1000);
        p2.setSacar(2000);
        p2.getMostrarDados();

        System.out.println("");

        Conta p3 = new Conta("Suárez", "123.321.098-44");
        p3.setSacar(100);
        p3.getMostrarDados();
    }
}
