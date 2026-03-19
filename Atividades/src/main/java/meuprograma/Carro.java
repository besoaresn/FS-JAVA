package meuprograma;

public class Carro {
    private String cor;
    private String modelo;

    public  Carro() {
        this.cor = "Branca";
        this.modelo = "Fusca";
    }

    public String getCor(){
        return this.cor;
    }

    public String getModelo(){
        return this.modelo;
    }

    public void setCor(String cor){
        this.cor = cor;
    }

    public void setModelo(String modelo){
        this.modelo = modelo;
    }

}
