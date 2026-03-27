package main.java.Model.Decorator;

public class KitMerchandising extends ExperienciasDecorator {
  private Ingresso ingresso;

  public KitMerchandising(Ingresso ingresso) {
    super(ingresso.getEvento(), ingresso.getPrecoBase());
    this.ingresso = ingresso;
  }

  @Override
  public double calcularValorFinal() {
    return ingresso.calcularValorFinal() + 50.0;
  }

  @Override
  public String getDescricaoTipo() {
    return ingresso.getDescricaoTipo() + " - Kit Merchandising";
  }
}
