package adaptee;

/**
 * Sistema legado de catálogo de áudio.
 *
 */
public class CatalogoLegado implements IAudioLegado {

  private String artista;
  private String titulo;
  private String duracao;

  public CatalogoLegado(String artista, String titulo, String duracao) {
    this.artista = artista;
    this.titulo = titulo;
    this.duracao = duracao;
  }

  @Override
  public String getDadosFormatados() {
    return artista + " - " + titulo + " (" + duracao + ")";
  }
}
