package main.java.Model.Decorator;
import Model.Ingresso;

public class KitMerchandising extends ExperienciasDecorator {
  
  public KitMerchandising(Ingresso ingresso) {
    super(ingresso);
  }

  @Override
  public double calcularValorFinal() {
    return ingressoDecorado.calcularValorFinal() + 50.0;
  }

  @Override
  public String getDescricaoTipo() {
    return ingressoDecorado.getDescricaoTipo() + " - Kit Merchandising";
  }
}
