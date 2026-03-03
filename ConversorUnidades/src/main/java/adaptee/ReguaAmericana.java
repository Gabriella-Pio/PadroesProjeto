package adaptee;

/**
 *
 * @author gabriella
 */
public class ReguaAmericana implements IMedidaImperial {
  private double polegadas;

  public ReguaAmericana(double polegadas) {
    this.polegadas = polegadas;
  }

  @Override
  public double getDistanciaEmPolegadas() {
    return polegadas;
  }
}
