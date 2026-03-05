package classededados;

public class Piloto {

  // Atributos: matricula, nome, pais de origem, idade, equipe que corre, motor do
  // carro, pontuação
  private String matricula;
  private String nome;
  private String paisOrigem;
  private int idade;
  private String equipe;
  private String motorCarro;
  private int pontuacao;

  // Construtor
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

  // Construtor matriz
  public Piloto(String[] dados) {
    String[] campos = dados[0].split(",");
    this.matricula = campos[0].trim();
    this.nome = campos[1].trim();
    this.paisOrigem = campos[2].trim();
    this.idade = Integer.parseInt(campos[3].trim());
    this.equipe = campos[4].trim();
    this.motorCarro = campos[5].trim();
    this.pontuacao = Integer.parseInt(campos[6].trim());
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
}
