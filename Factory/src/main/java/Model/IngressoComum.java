package Model;

public class IngressoComum extends Ingresso {

  public IngressoComum(String evento, double precoBase) {
    super(evento, precoBase);
  }

  @Override
  public double calcularValorFinal() {
    return precoBase;
  }

  @Override
  public String getDescricaoTipo() {
    return "Ingresso Comum";
  }
}
