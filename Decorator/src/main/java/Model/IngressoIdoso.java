package Model;

public class IngressoIdoso extends Ingresso {

  public IngressoIdoso(String evento, double precoBase) {
    super(evento, precoBase);
  }

  @Override
  public double calcularValorFinal() {
    return precoBase * 0.5;
  }

  @Override
  public String getDescricaoTipo() {
    return "Ingresso para Idosos";
  }
}
