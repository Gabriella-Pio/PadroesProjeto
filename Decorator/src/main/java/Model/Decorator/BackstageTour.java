package main.java.Model.Decorator;

public class BackstageTour extends ExperienciasDecorator {
  private Ingresso ingresso;

  public BackstageTour(Ingresso ingresso) {
    super(ingresso.getEvento(), ingresso.getPrecoBase());
    this.ingresso = ingresso;
  }

  @Override
  public double calcularValorFinal() {
    return ingresso.calcularValorFinal() + 100.0;
  }

  @Override
  public String getDescricaoTipo() {
    return ingresso.getDescricaoTipo() + " - Backstage Tour";
  }
}
