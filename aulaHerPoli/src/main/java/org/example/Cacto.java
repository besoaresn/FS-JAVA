package org.example;

public class Cacto extends Planta {

    public Cacto(String nome, double altura, Enum_especies especie) {
        super(nome, altura, especie);
    }

    public Cacto() {
        super();
    }

    @Override
    public void crescer() {
        setAltura(getAltura() + 5);
        System.out.println(getNome() + " cresceu 5 cm! Altura: " + getAltura() + " cm");
    }

    @Override
    public void obterEnergia() {
        System.out.println(getNome() + " armazena água e tem fotossíntese reduzida.");
    }
}

