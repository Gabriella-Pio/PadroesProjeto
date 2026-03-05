package classededados;

import java.util.Iterator;

import java.util.Stack;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Comparator;

import java.util.NoSuchElementException;

public class ColecaoDeObjetos implements Iterator {

  // Atributos para a estrutura de matriz
  private Piloto[][] matriz = null;
  private int line = 0;
  private int col = 0;

  // Outras estruturas de dados
  private LinkedList<Piloto> listaLinked = new LinkedList<>();
  private Stack<Piloto> pilhaStack = new Stack<>();
  private HashSet<Piloto> conjuntoSet = new HashSet<>();
  private PriorityQueue<Piloto> filaPrioridade = new PriorityQueue<>(Comparator.comparing(Piloto::getPontuacao)); // Prioriza
                                                                                                                  // a
                                                                                                                  // pontuação
  private TreeSet<Piloto> arvoreSet = new TreeSet<>(Comparator.comparing(Piloto::getMatricula));

  public ColecaoDeObjetos(Piloto[][] matriz) throws Exception {
    if (matriz == null)
      throw new Exception("Matriz nula");
    this.matriz = matriz;
    this.line = 0;
    this.col = 0;
  }

  @Override
  public boolean hasNext() {
    while (line < matriz.length) {
      if (col < matriz[line].length) {
        return true;
      }
      line++;
      col = 0;
    }
    return false;
  }

  @Override
  public Object next() {
    if (!hasNext())
      throw new NoSuchElementException();
    return matriz[line][col++];
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("Operação não suportada");
  }

  public void adicionarPiloto(Piloto p) {
    listaLinked.add(p);
    pilhaStack.push(p);
    conjuntoSet.add(p);
    filaPrioridade.add(p);
    arvoreSet.add(p);
  }

  // Métodos que retornam o Iterator de cada estrutura de forma padronizada
  public Iterator<Piloto> getLinkedListIterator() {
    return listaLinked.iterator();
  }

  public Iterator<Piloto> getStackIterator() {
    return pilhaStack.iterator();
  }

  public Iterator<Piloto> getHashSetIterator() {
    return conjuntoSet.iterator();
  }

  public Iterator<Piloto> getPriorityQueueIterator() {
    return filaPrioridade.iterator();
  }

  public Iterator<Piloto> getTreeSetIterator() {
    return arvoreSet.iterator();
  }
}
