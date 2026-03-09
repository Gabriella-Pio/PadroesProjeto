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

public class OrdenadoPorEnfaseNome extends AlunosPersistenciaTemplateMethod {
  
  public OrdenadoPorEnfaseNome(String nome) {
    super(nome);
  }

  @Override
  public boolean criterio(Aluno a1, Aluno a2) {
    String e1 = a1.getEnfase();
    String e2 = a2.getEnfase();
    int compEnfase = e1.compareToIgnoreCase(e2);

    String n1 = a1.getNome().getPrimeiroNome();
    String n2 = a2.getNome().getPrimeiroNome();
    int compNome = n1.compareToIgnoreCase(n2);

    if (compEnfase != 0) return compEnfase < 0;
    return compNome <= 0;
  }
}
