package meuprograma;

public class mainEx1 {
    public static void main(String[] args) {
        float pes, milhas;
        pes = calculoEx1.converterKmparaPes(382000);
        milhas = calculoEx1.converterKmparaMilhas(380000);
        System.out.println("380000 km é igual a " + pes + " pés");
        System.out.println("380000 km é igual a " + milhas + " milhas");
    }
}
