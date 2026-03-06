package target;

public class FaixaMusical implements IFaixaMusical {

  private String titulo;
  private String artista;
  private String duracao;

  public FaixaMusical(String titulo, String artista, String duracao) {
    this.titulo = titulo;
    this.artista = artista;
    this.duracao = duracao;
  }

  @Override
  public String getTitulo() {
    return titulo;
  }

  @Override
  public String getArtista() {
    return artista;
  }

  @Override
  public String getDuracao() {
    return duracao;
  }
}
