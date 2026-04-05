package Repository;

import main.java.Model.Decorator.MeetAndGreet;
import main.java.Model.Decorator.KitMerchandising;
import main.java.Model.Decorator.BackstageTour;

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

          // Aplica os Decorators conforme os sinalizadores no CSV
          for (int i = 3; i < dados.length; i++) {
            if (dados[i].trim().equals("MEET"))
              ing = new MeetAndGreet(ing);
            if (dados[i].trim().equals("KIT"))
              ing = new KitMerchandising(ing);
            if (dados[i].trim().equals("TOUR"))
              ing = new BackstageTour(ing);
          }

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

      // Adicionamos sinalizadores das Experiências no CSV
      if (ing.getDescricaoTipo().contains("Meet & Greet"))
        linha += ",MEET";
      if (ing.getDescricaoTipo().contains("Kit Merchandising"))
        linha += ",KIT";
      if (ing.getDescricaoTipo().contains("Backstage Tour"))
        linha += ",TOUR";

      pw.println(linha);
    } catch (Exception e) {
      throw new Exception("Erro ao salvar no CSV: " + e.getMessage());
    }
  }
}