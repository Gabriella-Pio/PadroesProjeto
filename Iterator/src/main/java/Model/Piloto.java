package Model;

public class Piloto {

  // Atributos: matricula, nome, pais de origem, idade, equipe que corre, motor do
  // carro, pontuação
  private int matricula;
  private String nome;
  private String paisOrigem;
  private int idade;
  private String equipe;
  private String motorCarro;
  private int pontuacao;

  // Construtores
  public Piloto(int matricula, String nome, String paisOrigem, int idade, String equipe, String motorCarro,
      int pontuacao) {
    this.matricula = matricula;
    this.nome = nome;
    this.paisOrigem = paisOrigem;
    this.idade = idade;
    this.equipe = equipe;
    this.motorCarro = motorCarro;
    this.pontuacao = pontuacao;
  }

  public Piloto(String linha) {
    String[] dados = linha.split(",");
    this.matricula = Integer.parseInt(dados[0].trim());
    this.nome = dados[1].trim();
    this.paisOrigem = dados[2].trim();
    this.idade = Integer.parseInt(dados[3].trim());
    this.equipe = dados[4].trim();
    this.motorCarro = dados[5].trim();
    this.pontuacao = Integer.parseInt(dados[6].trim());
  }

  // Getters e Setters
  public int getMatricula() {
    return matricula;
  }

  public void setMatricula(int matricula) {
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
}
