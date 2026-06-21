package org_desafio2.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Selecao {

    private final String id;
    private String nome;
    private final List<Jogador> jogadores = new ArrayList<>();

    public Selecao(String id, String nome) {
        this.id = Usuario.validarTexto(id, "ID");
        this.nome = Usuario.validarTexto(nome, "Nome");
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

    public void adicionarJogador(Jogador jogador) {
        if (jogador == null) {
            throw new IllegalArgumentException("Jogador nao pode ser nulo.");
        }
        if (!id.equals(jogador.getSelecao().getId())) {
            throw new IllegalArgumentException("Jogador pertence a outra selecao.");
        }
        jogadores.add(jogador);
    }

    public List<Jogador> getJogadores() {
        return Collections.unmodifiableList(jogadores);
    }

    @Override
    public String toString() {
        return "Selecao{"
                + "id='" + id + '\''
                + ", nome='" + nome + '\''
                + ", jogadores=" + jogadores.size()
                + '}';
    }
}
