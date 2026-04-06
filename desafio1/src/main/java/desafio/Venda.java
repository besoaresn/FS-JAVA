package desafio;

public class Venda {
    private double preco;
    private String idAnimal;


    public Venda() {
        this.setPreco(0);
        this.setidAnimal("0000");
    }

    public  Venda(double preco, String idAnimal) {
        this.preco = preco;
        this.idAnimal = idAnimal;
    }


    public void setPreco(double preco) {
        this.preco = preco;
    }
    public void setidAnimal(String idAnimal) {
        this.idAnimal = idAnimal;
    }

    public double getPreco() {
        return preco;
    }
    public String getidAnimal() {
        return idAnimal;
    }

}
