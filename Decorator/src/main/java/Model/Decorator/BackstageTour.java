package main.java.Model.Decorator;
import Model.Ingresso;

public class BackstageTour extends ExperienciasDecorator {

  public BackstageTour(Ingresso ingresso) {
    super(ingresso);
  }

  @Override
  public double calcularValorFinal() {
    return ingressoDecorado.calcularValorFinal() + 100.0;
  }

  @Override
  public String getDescricaoTipo() {
    return ingressoDecorado.getDescricaoTipo() + " - Backstage Tour";
  }
}
