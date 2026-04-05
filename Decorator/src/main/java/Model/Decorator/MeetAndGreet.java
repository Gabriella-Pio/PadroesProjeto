package main.java.Model.Decorator;
import Model.Ingresso;

public class MeetAndGreet extends ExperienciasDecorator {

  public MeetAndGreet(Ingresso ingresso) {
    super(ingresso);
  }

  @Override
  public double calcularValorFinal() {
    return ingressoDecorado.calcularValorFinal() + 150.0;
  }

  @Override
  public String getDescricaoTipo() {
    return ingressoDecorado.getDescricaoTipo() + " + Meet & Greet";
  }
}
