package Repository;

import Factory.IngressoFactory;
import Factory.Tipo;
import Iterator.VendasIterator;
import Model.Ingresso;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

public class VendasRepository {
  private String path;

  public VendasRepository(String path) {
    this.path = path;
  }

  public Iterator<Ingresso> obterVendas() throws Exception {
    ArrayList<Ingresso> array = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String linha;
      while ((linha = br.readLine()) != null) {
        if (!linha.trim().isEmpty()) {
          String[] dados = linha.split(",");

          // Extrai os dados do CSV
          String evento = dados[0].trim();
          Tipo tipo = Tipo.valueOf(dados[1].trim().toUpperCase());
          double preco = Double.parseDouble(dados[2].trim());

          // Cria o objeto a partir da Factory
          Ingresso ing = IngressoFactory.getIngresso(tipo, evento, preco);

          array.add(ing);
        }
      }
    }
    // Retorna o iterator para a Janela
    // return lista.iterator();
    return new VendasIterator(array);
  }

  public void salvarVenda(Ingresso ing, Tipo tipo) throws Exception {
    try (java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.FileWriter(path, true))) {
      String linha = ing.getEvento() + "," + tipo + "," + ing.getPrecoBase();
      pw.println(linha);
    } catch (Exception e) {
      throw new Exception("Erro ao salvar no CSV: " + e.getMessage());
    }
  }
}