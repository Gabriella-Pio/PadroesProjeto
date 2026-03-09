package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.ArrayList;

import classededados.Aluno;

public abstract class AlunosPersistenciaTemplateMethod {

  // Atributos
  private String caminhoArquivo;

  // Métodos
  public AlunosPersistenciaTemplateMethod(String nome) {
    caminhoArquivo = nome;
  }

  // Método abstrato
  public abstract boolean criterio(Aluno a1, Aluno a2);

  // Implemetar o Iterator (ver se é assim mesmo)
  public Iterator<Aluno> listarAlunos() throws Exception {
    try {
      ArrayList<Aluno> estrutura = new ArrayList<>();
      BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo));

      String linha = br.readLine();
      linha = br.readLine();

      while (linha != null) {
        estrutura.add(new Aluno(linha));
        linha = br.readLine();
      }
      br.close();

      for (int i = 0; i < estrutura.size(); i++) {
        for (int j = i + 1; j < estrutura.size(); j++) {
          if (!criterio(estrutura.get(i), estrutura.get(j))) {
            Aluno temp = estrutura.get(i);
            estrutura.set(i, estrutura.get(j));
            estrutura.set(j, temp);
          }
        }
      }
      return estrutura.iterator();
    } catch (

    Exception erro) {
      throw erro;
    }
  }
}
