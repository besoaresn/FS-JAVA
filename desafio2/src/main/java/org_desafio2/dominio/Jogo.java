package org_desafio2.dominio;

public class Jogo {

    private final String id;
    private final Selecao selecaoMandante;
    private final Selecao selecaoVisitante;
    private StatusJogo status = StatusJogo.ABERTO;
    private ResultadoJogo resultado;

    public Jogo(String id, Selecao selecaoMandante, Selecao selecaoVisitante) {
        this.id = Usuario.validarTexto(id, "ID");
        this.selecaoMandante = validarSelecao(selecaoMandante, "Selecao mandante");
        this.selecaoVisitante = validarSelecao(selecaoVisitante, "Selecao visitante");

        if (selecaoMandante.getId().equals(selecaoVisitante.getId())) {
            throw new IllegalArgumentException("Um jogo precisa ter duas selecoes diferentes.");
        }
    }

    public String getId() {
        return id;
    }

    public Selecao getSelecaoMandante() {
        return selecaoMandante;
    }

    public Selecao getSelecaoVisitante() {
        return selecaoVisitante;
    }

    public StatusJogo getStatus() {
        return status;
    }

    public ResultadoJogo getResultado() {
        return resultado;
    }

    public boolean estaAberto() {
        return status == StatusJogo.ABERTO;
    }

    public void encerrar(ResultadoJogo resultado) {
        if (resultado == null) {
            throw new IllegalArgumentException("Resultado nao pode ser nulo.");
        }
        this.resultado = resultado;
        this.status = StatusJogo.ENCERRADO;
    }

    public boolean envolveSelecao(String selecaoId) {
        return selecaoMandante.getId().equals(selecaoId)
                || selecaoVisitante.getId().equals(selecaoId);
    }

    private Selecao validarSelecao(Selecao selecao, String campo) {
        if (selecao == null) {
            throw new IllegalArgumentException(campo + " nao pode ser nula.");
        }
        return selecao;
    }

    @Override
    public String toString() {
        return "Jogo{"
                + "id='" + id + '\''
                + ", mandante=" + selecaoMandante.getNome()
                + ", visitante=" + selecaoVisitante.getNome()
                + ", status=" + status
                + '}';
    }
}
