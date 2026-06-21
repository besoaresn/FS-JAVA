package org_desafio2.dominio;

public class Administrador extends Usuario {

    public Administrador(String id, String nome, String email) {
        super(id, nome, email);
    }

    @Override
    public String toString() {
        return "Administrador{" + super.toString() + '}';
    }
}
