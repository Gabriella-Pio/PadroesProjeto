package Model;

// Classe abstrata "Product"
public abstract class Ingresso {
    private String evento;
    protected double precoBase;

    public Ingresso(String evento, double precoBase) {
        this.evento = evento;
        this.precoBase = precoBase;
    }

    public final String gerarRecibo() {
        return "---------- RECIBO ----------\n" +
                "EVENTO: " + this.getEvento() + "\n" +
                "CATEGORIA: " + this.getDescricaoTipo() + "\n" +
                "VALOR: R$ " + this.calcularValorFinal() + "\n" +
                "----------------------------";
    }

    public abstract double calcularValorFinal();

    public abstract String getDescricaoTipo();

    // Getters
    public String getEvento() {
        return evento;
    }

    public double getPrecoBase() {
        return precoBase;
    }
}