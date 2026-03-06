package target;

public class MensagemTexto implements IMensagemTexto {

  private String texto;

  public MensagemTexto(String texto) {
    this.texto = texto;
  }

  @Override
  public String getMensagemEmTexto() {
    return texto;
  }
}
