// • Por Nome;
// • Por Sobrenome;
// • Por Situação e Nome;
// • Por Curso e Nome;
// • Por Ênfase e Nome;
// • Por Curso, Ênfase e Nome;
// • Por Ênfase, Curso e Nome.
// • Situação, Ênfase, Curso e Nome.
package Repository;
import Model.Aluno;

public class OrdenadoPorSobrenome extends AlunosTemplateMethod {
  public OrdenadoPorSobrenome(String nome) {
    super(nome);
  }

  @Override
  public boolean criterio(Aluno a1, Aluno a2) {
    String s1 = a1.getNome().getSobrenome();
    String s2 = a2.getNome().getSobrenome();

    return s1.compareToIgnoreCase(s2) <= 0;
  }
}
