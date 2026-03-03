package classededados;

public class Piloto {

  private String matricula;
  private String nome;
  private String paisOrigem;
  private int idade;
  private String equipe;
  private String motorCarro;
  private int pontuacao;

  public Piloto(String matricula, String nome, String paisOrigem, int idade, String equipe, String motorCarro,
      int pontuacao) {
    this.matricula = matricula;
    this.nome = nome;
    this.paisOrigem = paisOrigem;
    this.idade = idade;
    this.equipe = equipe;
    this.motorCarro = motorCarro;
    this.pontuacao = pontuacao;
  }

  // Getters e Setters
  public String getMatricula() {
    return matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getPaisOrigem() {
    return paisOrigem;
  }

  public void setPaisOrigem(String paisOrigem) {
    this.paisOrigem = paisOrigem;
  }

  public int getIdade() {
    return idade;
  }

  public void setIdade(int idade) {
    this.idade = idade;
  }

  public String getEquipe() {
    return equipe;
  }

  public void setEquipe(String equipe) {
    this.equipe = equipe;
  }

  public String getMotorCarro() {
    return motorCarro;
  }

  public void setMotorCarro(String motorCarro) {
    this.motorCarro = motorCarro;
  }

  public int getPontuacao() {
    return pontuacao;
  }

  public void setPontuacao(int pontuacao) {
    this.pontuacao = pontuacao;
  }

  @Override
  public String toString() {
    return String.format("%-4s | %-20s | %-10s | %2d anos | %-15s | %-12s | %d pts",
        matricula, nome, paisOrigem, idade, equipe, motorCarro, pontuacao);
  }
}
