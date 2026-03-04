package classededados;

import java.util.Iterator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
// import java.util.Vector;
// import java.util.ArrayList;
import java.util.TreeSet;

import classededados.Piloto;

public class ColecaoDeObjetos {
  // private List<Piloto> listaArray = new ArrayList<>();
  // private List<Piloto> listaVector = new Vector<>();
  private LinkedList<Piloto> listaLinked = new LinkedList<>();
  private Stack<Piloto> pilhaStack = new Stack<>();
  private HashSet<Piloto> conjuntoSet = new HashSet<>();
  private PriorityQueue<Piloto> filaPrioridade = new PriorityQueue<>((p1, p2) -> p1.getNome().compareTo(p2.getNome()));
  private TreeSet<Piloto> arvoreSet = new TreeSet<>((p1, p2) -> p1.getMatricula().compareTo(p2.getMatricula()));

  public void adicionarPiloto(Piloto p) {
    // listaArray.add(p);
    // listaVector.add(p);
    listaLinked.add(p);
    pilhaStack.push(p);
    conjuntoSet.add(p);
    filaPrioridade.add(p);
    arvoreSet.add(p);
  }

  // Métodos que retornam o Iterator de cada estrutura de forma padronizada
  // public Iterator<Piloto> getArrayListIterator() {
  // return listaArray.iterator();
  // }

  // public Iterator<Piloto> getVectorIterator() {
  // return listaVector.iterator();
  // }

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
