package org_desafio2.dominio;

public abstract class Usuario {

    private final String id;
    private String nome;
    private String email;

    protected Usuario(String id, String nome, String email) {
        this.id = validarTexto(id, "ID");
        this.nome = validarTexto(nome, "Nome");
        this.email = validarTexto(email, "Email");
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = validarTexto(nome, "Nome");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = validarTexto(email, "Email");
    }

    protected static String validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " nao pode ser vazio.");
        }
        return valor.trim();
    }

    @Override
    public String toString() {
        return "id='" + id + '\''
                + ", nome='" + nome + '\''
                + ", email='" + email + '\'';
    }
}
