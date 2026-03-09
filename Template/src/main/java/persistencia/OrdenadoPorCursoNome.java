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

public class OrdenadoPorCursoNome extends AlunosPersistenciaTemplateMethod {

  public OrdenadoPorCursoNome(String nome) {
    super(nome);
  }

  @Override
  public boolean criterio(Aluno a1, Aluno a2) {
    String c1 = a1.getCurso().toString();
    String c2 = a2.getCurso().toString();

    int compCurso = c1.compareToIgnoreCase(c2);

    String n1 = a1.getNome().getPrimeiroNome();
    String n2 = a2.getNome().getPrimeiroNome();

    int compNome = n1.compareToIgnoreCase(n2);

    if (compCurso != 0) return compCurso < 0;
    return compNome <= 0;
  }
}
