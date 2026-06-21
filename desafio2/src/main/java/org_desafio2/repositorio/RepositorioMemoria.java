package org_desafio2.repositorio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RepositorioMemoria<T> {

    private final Map<String, T> dados = new LinkedHashMap<>();

    public void salvar(String id, T entidade) {
        validarId(id);
        String idNormalizado = normalizarId(id);
        if (entidade == null) {
            throw new IllegalArgumentException("Entidade nao pode ser nula.");
        }
        if (dados.containsKey(idNormalizado)) {
            throw new IllegalArgumentException("Ja existe registro com ID: " + id);
        }
        dados.put(idNormalizado, entidade);
    }

    public T buscarPorId(String id) {
        validarId(id);
        return dados.get(normalizarId(id));
    }

    public T obterPorId(String id, String nomeEntidade) {
        T entidade = buscarPorId(id);
        if (entidade == null) {
            throw new IllegalArgumentException(nomeEntidade + " nao encontrado: " + id);
        }
        return entidade;
    }

    public List<T> listar() {
        Collection<T> valores = dados.values();
        return new ArrayList<>(valores);
    }

    public boolean existe(String id) {
        validarId(id);
        return dados.containsKey(normalizarId(id));
    }

    public int tamanho() {
        return dados.size();
    }

    private void validarId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID nao pode ser vazio.");
        }
    }

    private String normalizarId(String id) {
        return id.trim().toUpperCase(Locale.ROOT);
    }
}
