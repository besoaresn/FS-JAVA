package meuprograma;

public class ex4 {
        public static void main(String[] args) {
            Cidades c1 = new Cidades("São Paulo", "SP", 12300000, 0);
            Cidades c2 = new Cidades("Rio de Janeiro", "RJ", 6748000, 0);
            Cidades c3 = new Cidades("Belo Horizonte", "MG", 2520000, 0);

            c1.mostrarDados();
            c2.mostrarDados();
            c3.mostrarDados();
        }
}