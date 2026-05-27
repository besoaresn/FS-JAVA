package org.example;

public class Erva extends Planta {

    public Erva(String nome, double altura, Enum_especies especie) {
        super(nome, altura, especie);
    }

    public Erva() {
        super();
    }

    @Override
    public void crescer() {
        setAltura(getAltura() + 20);
        System.out.println(getNome() + " cresceu 20 cm! Altura: " + getAltura() + " cm");
    }

    @Override
    public void obterEnergia() {
        System.out.println(getNome() + " absorve nutrientes do solo.");
    }
}


