package desafio;

public class Venda {
    private double preco;
    private String data;
    private String idAnimal;


    public Venda() {
        this.setPreco(0);
        this.setData("00/00/0000");
        this.setidAnimal("0000");
    }

    public  Venda(double preco, String data,  String idAnimal) {
        this.preco = preco;
        this.data = data;
        this.idAnimal = idAnimal;
    }


    public void setPreco(double preco) {
        this.preco = preco;
    }
    public void setData(String data) {
        this.data = data;
    }
    public void setidAnimal(String idAnimal) {
        this.idAnimal = idAnimal;
    }

    public double getPreco() {
        return preco;
    }
    public String getData() {
        return data;
    }
    public String getidAnimal() {
        return idAnimal;
    }

}
