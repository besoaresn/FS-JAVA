package org.example;

public class gerenciadorArray {

    private funcionario[] funcionarios;
    private int quantidade = 0;

    public gerenciadorArray(int tamanhoInicial) {
        if (tamanhoInicial <= 0) tamanhoInicial = 10;
        if (tamanhoInicial > 10) tamanhoInicial = 10; // limite máximo 10
        funcionarios = new funcionario[tamanhoInicial];
    }

    // Adiciona um funcionário (recebe o objeto)
    public void adicionar(funcionario f) {
        if (f == null) return;

        if (buscarPorNome(f.getNome()) != -1) {
            System.out.println("Funcionário já existe!");
            return;
        }

        if (quantidade >= funcionarios.length) {
            System.out.println("Array cheio! Limite atingido (máx: " + funcionarios.length + ")");
            return;
        }

        funcionarios[quantidade] = f;
        quantidade++;
        System.out.println("Funcionário adicionado: " + f.getNome());
    }


    private int buscarPorNome(String nome) {
        if (nome == null) return -1;
        for (int i = 0; i < quantidade; i++) {
            if (funcionarios[i].getNome().equalsIgnoreCase(nome)) {
                return i;
            }
        }
        return -1;
    }

    // Edita substituindo os dados do funcionário encontrado por 'novosDados'
    public boolean editar(String nome, funcionario novosDados) {
        int pos = buscarPorNome(nome);
        if (pos == -1) {
            System.out.println("Funcionário não encontrado!");
            return false;
        }
        funcionarios[pos] = novosDados;
        System.out.println("Funcionário atualizado: " + novosDados.getNome());
        return true;
    }

    // Remove pelo nome, deslocando elementos para não deixar buraco
    public boolean excluir(String nome) {
        int pos = buscarPorNome(nome);
        if (pos == -1) {
            System.out.println("Funcionário não encontrado!");
            return false;
        }

        for (int i = pos; i < quantidade - 1; i++) {
            funcionarios[i] = funcionarios[i + 1];
        }
        funcionarios[quantidade - 1] = null;
        quantidade--;
        System.out.println("Funcionário excluído: " + nome);
        return true;
    }

    public void mostrar() {
        if (quantidade == 0) {
            System.out.println("Nenhum funcionário cadastrado!");
            return;
        }

        System.out.println("\n========== LISTA DE FUNCIONÁRIOS ==========");
        for (int i = 0; i < quantidade; i++) {
            System.out.println("Nome: " + funcionarios[i].getNome());
            System.out.println("Cargo: " + funcionarios[i].getCargo());
            System.out.println("Salário: R$ " + funcionarios[i].getSalario());
            System.out.println("----------------------------------------");
        }
        System.out.println("========================================");
    }
}