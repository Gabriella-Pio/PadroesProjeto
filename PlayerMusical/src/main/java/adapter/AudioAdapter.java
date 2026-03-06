package adapter;

import adaptee.IAudioLegado;
import target.IFaixaMusical;

public class AudioAdapter implements IFaixaMusical {

  private IAudioLegado catalogoLegado;

  public AudioAdapter(IAudioLegado catalogoLegado) {
    this.catalogoLegado = catalogoLegado;
  }

  @Override
  public String getTitulo() {
    return parsear()[1];
  }

  @Override
  public String getArtista() {
    return parsear()[0];
  }

  @Override
  public String getDuracao() {
    return parsear()[2];
  }

  private String[] parsear() {
    String dados = catalogoLegado.getDadosFormatados();
    try {
      // Separa artista do resto pelo primeiro " - "
      int sepArtista = dados.indexOf(" - ");
      String artista = dados.substring(0, sepArtista).trim();

      // O resto é "Título (MM:SS)"
      String resto = dados.substring(sepArtista + 3).trim();

      // Separa duração pelo último "("
      int sepDuracao = resto.lastIndexOf("(");
      String titulo = resto.substring(0, sepDuracao).trim();
      String duracao = resto.substring(sepDuracao + 1).replace(")", "").trim();

      return new String[] { artista, titulo, duracao };
    } catch (Exception e) {
      return new String[] { "Desconhecido", dados, "--:--" };
    }
  }
}
