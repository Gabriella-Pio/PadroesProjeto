package classededados;

import classededados.Curso;

public class Aluno {
  // Atributos: nome, curso, situacao, enfase
  private Nome nome;
  private Curso curso;
  private boolean situacao;
  private String enfase;

  // Construtor
  public Aluno(Nome nome, Curso curso, boolean situacao, String enfase) {
    this.nome = nome;
    this.curso = curso;
    this.situacao = situacao;
    this.enfase = enfase;
  }

  public Aluno(String linha) {
    String[] dados = linha.split(";");
    
    this.nome = new Nome(dados[0].trim());
    this.curso = Curso.fromCodigo(Integer.parseInt(dados[1].trim()));
    this.situacao = dados[2].trim().trim().equalsIgnoreCase("Sim"); // Considera "Sim" como true, qualquer outra coisa como false
    this.enfase = dados[3].trim();
  }

  // Getters e Setters
  public Nome getNome() {
    return nome;
  }

  public void setNome(Nome nome) {
    this.nome = nome;
  }

  public Curso getCurso() {
    return curso;
  }

  public void setCurso(Curso curso) {
    this.curso = curso;
  }

  public boolean getSituacao() {
    return situacao;
  }

  public void setSituacao(boolean situacao) {
    this.situacao = situacao;
  }

  public String getEnfase() {
    return enfase;
  }

  public void setEnfase(String enfase) {
    this.enfase = enfase;
  }
}
