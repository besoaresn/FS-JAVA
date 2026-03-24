package meuprograma;

public class Conta {
    private final String nome;
    private final String cpf;
    private  float saldo;

    public Conta(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
        this.saldo = 0;
    }

    public void realizarDeposito (float valor){
        if (valor > 0){
            this.saldo += valor;
            System.out.println("O depósito de " +valor+ " foi realizado");
        } else {
            System.out.println("Valor de depósito não pode ser abaixo de 0");
        }
    }

    public void realizarSaque (float valor){
        if (saldo <= 0){
            System.out.println("Saldo insuficiente");
            return;
        }

        if (valor > saldo){
            System.out.println("Você não pode sacar um valor maior que seu saldo");
            return;
        }

        if (valor > 0){
            this.saldo -= valor;
            System.out.println("O saque de R$ " + valor + " foi realizado");
        } else {
            System.out.println("Valor deve ser maior que 0");
        }
    }

    public void mostrarDados(){
        System.out.println("Nome: " + this.nome);
        System.out.println("Cpf: " + this.cpf);
        System.out.println("Saldo atual: R$" + this.saldo);
    }

    public String toString(){
        return "Nome: " + this.nome+" Cpf: " + this.cpf+" Saldo: " + this.saldo;
    }
}
