package Model;

import java.time.LocalDate;

public class IngressoComum extends Ingresso {

  public IngressoComum(String evento, double precoBase) {
    super(evento, precoBase);
  }

  @Override
  public double calcularValorFinal() {
    return precoBase; // Comum não tem desconto nem acréscimo
  }

  @Override
  public String getDescricaoTipo() {
    return "Ingresso Comum";
  }
}
