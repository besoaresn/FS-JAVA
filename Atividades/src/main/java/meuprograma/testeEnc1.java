package meuprograma;

public class testeEnc1 {
    public static void main(String[] args) {

        Carro carro1 = new Carro();
        System.out.println("Modelo do Carro 1: "+carro1.getModelo());
        carro1.setModelo("Onix");
        System.out.println("Modelo do carro 1: "+carro1.getModelo());

    }
}
