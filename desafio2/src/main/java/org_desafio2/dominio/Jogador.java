package org_desafio2.dominio;

public class Jogador {

    private final String id;
    private String nome;
    private Posicao posicao;
    private final Selecao selecao;

    public Jogador(String id, String nome, Posicao posicao, Selecao selecao) {
        this.id = Usuario.validarTexto(id, "ID");
        this.nome = Usuario.validarTexto(nome, "Nome");
        this.posicao = validarPosicao(posicao);
        this.selecao = validarSelecao(selecao);
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = Usuario.validarTexto(nome, "Nome");
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = validarPosicao(posicao);
    }

    public Selecao getSelecao() {
        return selecao;
    }

    private Posicao validarPosicao(Posicao posicao) {
        if (posicao == null) {
            throw new IllegalArgumentException("Posicao nao pode ser nula.");
        }
        return posicao;
    }

    private Selecao validarSelecao(Selecao selecao) {
        if (selecao == null) {
            throw new IllegalArgumentException("Selecao nao pode ser nula.");
        }
        return selecao;
    }

    @Override
    public String toString() {
        return "Jogador{"
                + "id='" + id + '\''
                + ", nome='" + nome + '\''
                + ", posicao=" + posicao
                + ", selecao=" + selecao.getNome()
                + '}';
    }
}
