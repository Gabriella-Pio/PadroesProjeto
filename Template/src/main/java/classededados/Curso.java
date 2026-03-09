package classededados;

public enum Curso {
  ENGENHARIA_DE_SOFTWARE(28, "Engenharia de Software"),
  ANALISE_E_DESENVOLVIMENTO_DE_SISTEMAS(33, "Análise e Desenvolvimento de Sistemas"),
  DESCONHECIDO(0, "Não Informado");

  private final int codigo;
  private final String nome;

  Curso(int codigo, String nome) {
    this.codigo = codigo;
    this.nome = nome;
  }

  // Método estático para obter o curso a partir do código
  public static Curso fromCodigo(int codigo) {
    for (Curso curso : Curso.values()) {
      if (curso.codigo == codigo) {
        return curso;
      }
    }
    return DESCONHECIDO;
  }

  // Getters
  public int getCodigo() {
    return codigo;
  }

  public String getNome() {
    return nome;
  }

  @Override
  public String toString() {
    return nome;
  }
}

