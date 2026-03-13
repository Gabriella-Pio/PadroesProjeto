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
import Repository.AlunosTemplateMethod;

public class OrdenadoPorSituacaoNome extends AlunosTemplateMethod {

  public OrdenadoPorSituacaoNome(String nome) {
    super(nome);
  }
 
  @Override
  public boolean criterio(Aluno a1, Aluno a2) {
    boolean s1 = a1.getSituacao();
    boolean s2 = a2.getSituacao();

    int compSituacao = Boolean.compare(s1, s2);

    String n1 = a1.getNome().getPrimeiroNome();
    String n2 = a2.getNome().getPrimeiroNome(); 

    int compNome = n1.compareToIgnoreCase(n2);

    if (compSituacao != 0) return compSituacao < 0;
    return compNome <= 0;
  }
}
