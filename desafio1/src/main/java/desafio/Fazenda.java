package desafio;

import java.util.ArrayList;
import java.util.List;

public class Fazenda {
    private List<Animal> animais;
    private int proximoId;

    public Fazenda() {
        this.animais = new ArrayList<>();
        proximoId = 1000;
    }

public void incluirAnimal(Animal animal) {
    if (animal == null) {
        throw new IllegalArgumentException("Animal não pode ser nulo");
    }
    if (animal.getId() == null || animal.getId().isBlank()) {
        animal.setId(String.valueOf(proximoId));
        proximoId++;
    }
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

public void avaliarAnimal(String id, double altura, double comprimento, double largura, double peso) {
    if (id == null || id.isBlank()) {
        throw new IllegalArgumentException("ID do animal não pode ser nulo ou vazio");
    }
    if (altura <= 0 || comprimento <= 0 || largura <= 0 || peso <= 0) {
        throw new IllegalArgumentException("As medidas do animal devem ser maiores que zero");
    }

    Animal a = consultarAnimal(id);
    if (a == null) {
        throw new IllegalArgumentException("Animal com ID " + id + " não encontrado");
    }
    a.setAltura(altura);
    a.setComprimento(comprimento);
    a.setLargura(largura);
    a.setPeso(peso);
}

public void registrarVenda(String id, double valor){
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID do animal não pode ser nulo ou vazio");
        }


}

}



