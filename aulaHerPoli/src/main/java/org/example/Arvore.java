package org.example;

public class Arvore extends Planta {

    public Arvore(String nome, double altura, Enum_especies especie) {
        super(nome, altura, especie);
    }

    public Arvore() {
        super();
    }

    @Override
    public void crescer() {
        setAltura(getAltura() + 50);
        System.out.println(getNome() + " cresceu 50 cm! Altura: " + getAltura() + " cm");
    }

    @Override
    public void obterEnergia() {
        System.out.println(getNome() + " faz fotossíntese nas folhas.");
    }
}


