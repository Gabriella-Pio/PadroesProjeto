package Model;

public class IngressoMeia extends Ingresso {

  public IngressoMeia(String evento, double precoBase) {
    super(evento, precoBase);
  }

  @Override
  public double calcularValorFinal() {
    return precoBase * 0.5;
  }

  @Override
  public String getDescricaoTipo() {
    return "Meia-Entrada";
  }
}
