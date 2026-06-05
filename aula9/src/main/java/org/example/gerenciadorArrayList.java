package org.example;

import java.util.ArrayList;

public class gerenciadorArrayList {

    private ArrayList<funcionario> funcionarios;

    public gerenciadorArrayList() {
        funcionarios = new ArrayList<>();
    }

    public void adicionar(funcionario f) {
        if (f == null) return;
        if (buscarPorNome(f.getNome()) != -1) {
            System.out.println("Funcionário já existe!");
            return;
        }
        funcionarios.add(f);
        System.out.println("Funcionário adicionado: " + f.getNome());
    }

    private int buscarPorNome(String nome) {
        if (nome == null) return -1;
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getNome().equalsIgnoreCase(nome)) {
                return i;
            }
        }
        return -1;
    }

    public boolean editar(String nome, funcionario novosDados) {
        int pos = buscarPorNome(nome);
        if (pos == -1) {
            System.out.println("Funcionário não encontrado!");
            return false;
        }
        funcionarios.set(pos, novosDados);
        System.out.println("Funcionário atualizado: " + novosDados.getNome());
        return true;
    }

    public boolean excluir(String nome) {
        int pos = buscarPorNome(nome);
        if (pos == -1) {
            System.out.println("Funcionário não encontrado!");
            return false;
        }
        funcionarios.remove(pos);
        System.out.println("Funcionário excluído: " + nome);
        return true;
    }

    public void mostrar() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado!");
            return;
        }
        System.out.println("\n========== LISTA DE FUNCIONÁRIOS (ArrayList) ==========");
        for (funcionario f : funcionarios) {
            System.out.println("Nome: " + f.getNome());
            System.out.println("Cargo: " + f.getCargo());
            System.out.println("Salário: R$ " + f.getSalario());
            System.out.println("----------------------------------------");
        }
        System.out.println("========================================");
    }
}
