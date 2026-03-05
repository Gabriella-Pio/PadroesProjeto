package persistencia;

import classededados.Piloto;
import classededados.ColecaoDeObjetos;
import java.io.BufferedReader;
import java.io.FileReader;

public class PilotosPersistencia {
  // Caminho do arquivo
  private String path;

  public PilotosPersistencia(String path) {
    this.path = path;
  }

  private int contarLinhas() throws Exception {
    int count = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      while (br.readLine() != null)
        count++;
    }
    return count;
  }

  public ColecaoDeObjetos carregarPilotos() throws Exception {
    int totalLinhas = contarLinhas();

    Piloto[][] matriz = new Piloto[totalLinhas][1];

    ColecaoDeObjetos colecao = new ColecaoDeObjetos(matriz);

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String linha;
      int i = 0;

      while ((linha = br.readLine()) != null) {
        String[] dados = linha.split(",");
        // Criação do objeto Piloto
        Piloto p = new Piloto(
            dados[0].trim(), // Matrícula
            dados[1].trim(), // Nome
            dados[2].trim(), // País de Origem
            Integer.parseInt(dados[3].trim()), // Idade
            dados[4].trim(), // Equipe que corre
            dados[5].trim(), // Motor do carro
            Integer.parseInt(dados[6].trim()) // Pontuação
        );

        // Alimenta a matriz
        matriz[i][0] = p;
        i++;

        // Alimenta as outras estruturas
        colecao.adicionarPiloto(p);
      }
    } catch (Exception e) {
      throw new Exception("Erro ao carregar pilotos: " + e.getMessage());
    }

    return colecao;
  }
}