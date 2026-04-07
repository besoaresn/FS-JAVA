package org.desafio1;

public class Animal {
    private String id;
    private tipoAnimal tipo;
    private double altura;
    private double comprimento;
    private double largura;
    private double peso;
    private StatusAnimal status;


    public Animal() {
        this.setId("0000");
        this.tipo = tipoAnimal.DESCONHECIDO;
        this.setAltura(0);
        this.setComprimento(0);
        this.setLargura(0);
        this.setPeso(0);
        this.status = StatusAnimal.ATIVO;

    }


    public Animal(String id, tipoAnimal tipo, double altura, double comprimento, double largura, double peso) {
        this.id = id;
        this.tipo = tipo;
        this.altura = altura;
        this.comprimento = comprimento;
        this.largura = largura;
        this.peso = peso;
        this.status = StatusAnimal.ATIVO;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setTipo(tipoAnimal tipo) {
        this.tipo = tipo;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setStatus(StatusAnimal status) {
        this.status = status;
    }


    public String getId() {
        return this.id;
    }

    public tipoAnimal getTipo() {
        return this.tipo;
    }

    public double getAltura() {
        return this.altura;
    }

    public double getComprimento() {
        return this.comprimento;
    }

    public double getLargura() {
        return this.largura;
    }

    public double getPeso() {
        return this.peso;
    }

    public StatusAnimal getStatus() {
        return this.status;
    }
}


