package main.java.Model.Decorator;

import Model.Ingresso;

public abstract class ExperienciasDecorator extends Ingresso {

  protected Ingresso ingressoDecorado;

  public ExperienciasDecorator(Ingresso ingresso) {
    super(ingresso.getEvento(), ingresso.getPrecoBase());
    this.ingressoDecorado = ingresso;
  }
}