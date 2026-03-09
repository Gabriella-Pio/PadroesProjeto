// • Por Nome;
// • Por Sobrenome;
// • Por Situação e Nome;
// • Por Curso e Nome;
// • Por Ênfase e Nome;
// • Por Curso, Ênfase e Nome;
// • Por Ênfase, Curso e Nome.
// • Situação, Ênfase, Curso e Nome.

package persistencia;

import classededados.Aluno;
import persistencia.AlunosPersistenciaTemplateMethod;

public class OrdenadoPorNome extends AlunosPersistenciaTemplateMethod {
  public OrdenadoPorNome(String nome) {
    super(nome);
  }

  @Override
  public boolean criterio(Aluno a1, Aluno a2) {
    String n1 = a1.getNome().getPrimeiroNome();
    String n2 = a2.getNome().getPrimeiroNome();

    return (n1.compareToIgnoreCase(n2) <= 0);
  }
}
