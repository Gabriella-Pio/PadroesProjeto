package Model;

import java.time.LocalDate;

public class IngressoIdoso extends Ingresso {

  public IngressoIdoso(String evento, double precoBase) {
    super(evento, precoBase);
  }

  @Override
  public double calcularValorFinal() {
    return precoBase * 0.5; // Idosos têm 50% de desconto
  }

  @Override
  public String getDescricaoTipo() {
    return "Ingresso para Idosos";
  }
}
