package meuprograma;

public class Cidades {
    String nome;
    String sigla;
    int populacao;
    float area;

    public  Cidades(){
        this.setNome("desconhecido");
        this.setSigla("desconhecido");
        this.setPopulacao(0);
        this.setArea(0);
    }

    public  Cidades(String nome, String sigla, int populacao, float area){
        this.nome = nome;
        this.sigla = sigla;
        this.populacao = 0;
        this.area = 0;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setSigla(String sigla){
        this.sigla = sigla;
    }

    public void setPopulacao(int populacao){
        this.populacao = populacao;
    }

    public void setArea(float area ){
        this.area = area;
    }

    public String getNome(){
       return this.nome;
    }

    public String  getSigla(){
        return this.sigla;
    }

    public int getPopulacao(){
        return this.populacao;
    }

    public float getArea(){
        return this.area;
    }

    public void mostrarDados(){
        System.out.println("Nome: " + this.nome);
        System.out.println("Sigla: " + this.sigla);
        System.out.println("População: " + this.populacao);
        System.out.println("Área: " + this.area);
    }

    public String toString(){
        return "Nome: " + this.nome+" Sigla: " + this.sigla+" População: " + this.populacao+" Área: " + this.area;
    }


}
