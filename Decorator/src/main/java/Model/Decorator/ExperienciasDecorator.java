package main.java.Model.Decorator;

import Model.Ingresso;

public abstract class ExperienciasDecorator extends Ingresso {

  public ExperienciasDecorator(String evento, double precoBase) {
    super(evento, precoBase);
  }

  public abstract String getDescricaoTipo();
}