package main.java.Model.Decorator;

import Model.Ingresso;

public class MeetAndGreet extends ExperienciasDecorator {
  private Ingresso ingresso;

  public MeetAndGreet(Ingresso ingresso) {
    super(ingresso.getEvento(), ingresso.getPrecoBase());
    this.ingresso = ingresso;
  }

  @Override
  public double calcularValorFinal() {
    return ingresso.calcularValorFinal() + 150.0;
  }

  @Override
  public String getDescricaoTipo() {
    return ingresso.getDescricaoTipo() + " + Meet & Greet";
  }
}
