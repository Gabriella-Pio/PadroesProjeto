package Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;

import Model.Aluno;

import java.util.ArrayList;

public abstract class AlunosTemplateMethod {

  // Atributos
  private String caminhoArquivo;

  // Métodos
  public AlunosTemplateMethod(String nome) {
    caminhoArquivo = nome;
  }

  // Método abstrato
  public abstract boolean criterio(Aluno a1, Aluno a2);

  
  public Iterator<Aluno> listarAlunos() throws Exception {
    try {
      ArrayList<Aluno> array = new ArrayList<>();
      BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo));
      String linha = br.readLine();
      linha = br.readLine();

      while (linha != null) {
        array.add(new Aluno(linha));
        linha = br.readLine();
      }
      br.close();

      for (int i = 0; i < array.size(); i++) {
        for (int j = i + 1; j < array.size(); j++) {
          if (!criterio(array.get(i), array.get(j))) {
            Aluno temp = array.get(i);
            array.set(i, array.get(j));
            array.set(j, temp);
          }
        }
      }
      return array.iterator();
    } catch (

    Exception erro) {
      throw erro;
    }
  }
}
