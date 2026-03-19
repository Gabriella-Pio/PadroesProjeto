package Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeSet;

import Iterator.MatrizContainer;
import Iterator.FilaIterator;
import Iterator.PilhaIterator;
import Model.Piloto;

public class PilotosRepository {
  // Caminho do arquivo
  private String path;

  public PilotosRepository(String path) {
    this.path = path;
  }

  public Iterator<Piloto> getPilhaIterator() throws Exception {
    Stack<Piloto> pilha = new Stack<>();
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String linha;
      while ((linha = br.readLine()) != null) {
        Piloto p = new Piloto(linha);
        pilha.push(p);
      }
    } catch (Exception e) {
      throw new Exception("Erro ao carregar pilotos - Pilha: " + e.getMessage());
    }
    return new PilhaIterator(pilha);
  }

  public Iterator<Piloto> getFilaIterator() throws Exception {
    Queue<Piloto> fila = new LinkedList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String linha;
      while ((linha = br.readLine()) != null) {
        Piloto p = new Piloto(linha);
        fila.add(p);
      }
    } catch (Exception e) {
      throw new Exception("Erro ao carregar pilotos - Fila: " + e.getMessage());
    }
    return new FilaIterator(fila);
  }

  public Iterator<Piloto> getArvoreIterator() throws Exception {
    TreeSet<Piloto> arvore = new TreeSet<>((p1, p2) -> Integer.compare(p1.getMatricula(), p2.getMatricula()));
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String linha;
      while ((linha = br.readLine()) != null) {
        Piloto p = new Piloto(linha);
        arvore.add(p);
      }
    } catch (Exception e) {
      throw new Exception("Erro ao carregar pilotos - TreeSet: " + e.getMessage());
    }
    return arvore.iterator();
  }

  public Iterator<Piloto> getHashMapIterator() throws Exception {
    HashMap<Piloto, Void> hash = new HashMap<>();
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String linha;
      while ((linha = br.readLine()) != null) {
        Piloto p = new Piloto(linha);
        hash.put(p, null);
      }
    } catch (Exception e) {
      throw new Exception("Erro ao carregar pilotos - HashMap: " + e.getMessage());
    }
    return hash.keySet().iterator();
  }

  public Iterator<Piloto> getLinkedListIterator() throws Exception {
    LinkedList<Piloto> linkedList = new LinkedList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String linha;
      while ((linha = br.readLine()) != null) {
        Piloto p = new Piloto(linha);
        linkedList.add(p);
      }
    } catch (Exception e) {
      throw new Exception("Erro ao carregar pilotos - LinkedList: " + e.getMessage());
    }
    return linkedList.iterator();
  }

  public Iterator<Piloto> getMatrizIterator() throws Exception {
    int totalLinhas = contarLinhas();

    Piloto[][] matriz = new Piloto[totalLinhas][1];
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String linha;
      int i = 0;

      while ((linha = br.readLine()) != null) {
        Piloto p = new Piloto(linha);
        matriz[i][0] = p;
        i++;
      }
    } catch (Exception e) {
      throw new Exception("Erro ao carregar pilotos - Matriz: " + e.getMessage());
    }
    return new MatrizContainer(matriz).getMatriz();
  }

  // Método auxiliar para a matriz
  private int contarLinhas() throws Exception {
    int count = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      while (br.readLine() != null)
        count++;
    }
    return count;
  }
}