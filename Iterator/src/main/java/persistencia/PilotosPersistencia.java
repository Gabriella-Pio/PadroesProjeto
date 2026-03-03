package persistencia;

import classededados.Piloto;
import classededados.ColecaoDeObjetos;
import java.io.BufferedReader;
import java.io.FileReader;

public class PilotosPersistencia {
  private String path;

  public PilotosPersistencia(String path) {
    this.path = path;
  }

  public ColecaoDeObjetos carregarPilotos() throws Exception {
    ColecaoDeObjetos colecao = new ColecaoDeObjetos();
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String linha;
      while ((linha = br.readLine()) != null) {
        String[] dados = linha.split(",");
        // Matricula, Nome, Pais de Origem, Idade, Equipe que corre, Motor do carro, Pontuação
        Piloto p = new Piloto(dados[0], dados[1], dados[2], Integer.parseInt(dados[3].trim()), dados[4], dados[5],
            Integer.parseInt(dados[6].trim()));
        colecao.adicionarPiloto(p);
      }
    }
    return colecao;
  }
}