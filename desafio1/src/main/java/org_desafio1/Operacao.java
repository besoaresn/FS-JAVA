package org_desafio1;

public class Operacao {
    private TipoOperacao tipo;
    private double valor;
    private String idAnimal;

    public Operacao() {
        this.setTipo(TipoOperacao.VENDA);
        this.setValor(0);
        this.setIdAnimal("0000");
    }

    public Operacao(TipoOperacao tipo, double valor, String idAnimal) {
        this.tipo = tipo;
        this.valor = valor;
        this.idAnimal = idAnimal;
    }

    public void setTipo(TipoOperacao tipo) {
        this.tipo = tipo;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setIdAnimal(String idAnimal) {
        this.idAnimal = idAnimal;
    }

    public TipoOperacao getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public String getIdAnimal() {
        return idAnimal;
    }
}

