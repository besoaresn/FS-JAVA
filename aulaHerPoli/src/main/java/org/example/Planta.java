package org.example;

public class Planta {
    private String nome;
    private double altura;
    private Enum_especies especie;

    // Construtor com parâmetros
    public Planta(String nome, double altura, Enum_especies especie) {
        setNome(nome);
        setAltura(altura);
        setEspecie(especie);
    }

    // Construtor sem parâmetros
    public Planta() {
        this("Desconhecida", 0, null);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome != null ? nome : "Desconhecida";
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura >= 0 ? altura : 0;
    }

    public Enum_especies getEspecie() {
        return especie;
    }

    public void setEspecie(Enum_especies especie) {
        if (especie == null) {
            System.out.println("Espécie inválida! Definindo como desconhecida.");
            this.especie = null;
        } else {
            this.especie = especie;
        }
    }

    public void crescer() {
        System.out.println(nome + " está crescendo...");
    }

    public void obterEnergia() {
        System.out.println(nome + " obtendo energia.");
    }

    public void exibirInfo() {
        System.out.println("Nome: " + nome);
        System.out.println("Espécie: " + especie);
        System.out.println("Altura: " + altura + " cm");
    }
}


