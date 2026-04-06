package org.desafio1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fazenda {
    private final List<Animal> animais;
    private final List<Operacao> operacoes;
    private int proximoId;

    public Fazenda() {
        this.animais = new ArrayList<>();
        this.operacoes = new ArrayList<>();
        proximoId = 1000;
    }

public void incluirAnimal(Animal animal) {
    if (animal == null) {
        throw new IllegalArgumentException("Animal não pode ser nulo");
    }
    validarMedidasPositivas(animal.getAltura(), animal.getComprimento(), animal.getLargura(), animal.getPeso());

    if (animal.getId() == null || animal.getId().isBlank()) {
        while (consultarAnimal(String.valueOf(proximoId)) != null) {
            proximoId++;
        }
        animal.setId(String.valueOf(proximoId));
        proximoId++;
    } else if (consultarAnimal(animal.getId()) != null) {
        throw new IllegalArgumentException("ID " + animal.getId() + " já cadastrado");
    }

    animal.setStatus(StatusAnimal.ATIVO);
    this.animais.add(animal);
}

public Animal consultarAnimal(String id) {
    if (id == null || id.isBlank()) {
        throw new IllegalArgumentException("ID do animal não pode ser nulo ou vazio");
    }
    for (Animal a : this.animais){
        if (a.getId().equals(id)) {
            return a;
        }
    }
    return null;
}

public String avaliarAnimal(String id, double altura, double comprimento, double largura, double peso) {
    if (id == null || id.isBlank()) {
        throw new IllegalArgumentException("ID do animal não pode ser nulo ou vazio");
    }
    validarMedidasPositivas(altura, comprimento, largura, peso);

    Animal a = consultarAnimal(id);
    if (a == null) {
        throw new IllegalArgumentException("Animal com ID " + id + " não encontrado");
    }
    if (a.getStatus() != StatusAnimal.ATIVO) {
        throw new IllegalStateException("Apenas animais ativos podem ser avaliados");
    }

    if (altura < a.getAltura() || comprimento < a.getComprimento() || largura < a.getLargura() || peso < a.getPeso()) {
        return "ALERTA: Medidas inferiores ao último registro. Encaminhar para exame veterinário.";
    }

    a.setAltura(altura);
    a.setComprimento(comprimento);
    a.setLargura(largura);
    a.setPeso(peso);
    return null;
}

public void registrarVenda(String id, double valor){
    if  (id == null || id.isBlank()) {
        throw new IllegalArgumentException("ID do animal não pode ser nulo ou vazio");
    }
    if (valor <= 0 ) {
        throw new IllegalArgumentException("Valor do animal deve ser maior");
    }
    Animal a = consultarAnimal(id);
    if (a == null) {
        throw new IllegalArgumentException("Animal com ID " + id + " não encontrado");
    }
    if (a.getStatus() != StatusAnimal.ATIVO) {
        throw new IllegalStateException("Animal com ID " + id + " não está disponível para venda");
    }

    a.setStatus(StatusAnimal.VENDIDO);
    Operacao operacao = new Operacao(TipoOperacao.VENDA, valor, id);
    this.operacoes.add(operacao);
}

public void registrarPerda(String id) {
    if (id == null || id.isBlank()) {
        throw new IllegalArgumentException("ID do animal não pode ser nulo ou vazio");
    }
    Animal a = consultarAnimal(id);
    if (a == null) {
        throw new IllegalArgumentException("Animal com ID " + id + " não encontrado");
    }
    if (a.getStatus() != StatusAnimal.ATIVO) {
        throw new IllegalStateException("Animal com ID " + id + " não está ativo");
    }
    a.setStatus(StatusAnimal.PERDIDO);
    Operacao operacao = new Operacao(TipoOperacao.PERDA, 0, id);
    this.operacoes.add(operacao);
}

public List<Animal> relatorioAnimaisAtivosPorTipo(tipoAnimal tipo) {
    if (tipo == null || tipo == tipoAnimal.DESCONHECIDO) {
        throw new IllegalArgumentException("Tipo inválido para relatório");
    }
    List<Animal> resultado = new ArrayList<>();
    for (Animal a : this.animais) {
        if (a.getTipo() == tipo && a.getStatus() == StatusAnimal.ATIVO) {
            resultado.add(a);
        }
    }
    return resultado;
}

public List<Operacao> relatorioVendas() {
    List<Operacao> vendas = new ArrayList<>();
    for (Operacao operacao : this.operacoes) {
        if (operacao.getTipo() == TipoOperacao.VENDA) {
            vendas.add(operacao);
        }
    }
    return Collections.unmodifiableList(vendas);
}

public List<Animal> relatorioPerdas() {
    List<Animal> perdidos = new ArrayList<>();
    for (Animal a : this.animais) {
        if (a.getStatus() == StatusAnimal.PERDIDO) {
            perdidos.add(a);
        }
    }
    return Collections.unmodifiableList(perdidos);
}

public double somatorioVendas() {
    double total = 0;
    for (Operacao operacao : this.operacoes) {
        if (operacao.getTipo() == TipoOperacao.VENDA) {
            total += operacao.getValor();
        }
    }
    return total;
}

public int contagemPerdasPorTipo(tipoAnimal tipo) {
    int total = 0;
    for (Animal a : this.animais) {
        if (a.getStatus() == StatusAnimal.PERDIDO && a.getTipo() == tipo) {
            total++;
        }
    }
    return total;
}


private void validarMedidasPositivas(double altura, double comprimento, double largura, double peso) {
    if (altura <= 0 || comprimento <= 0 || largura <= 0 || peso <= 0) {
        throw new IllegalArgumentException("As medidas do animal devem ser maiores que zero");
    }
}

}