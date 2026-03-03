package classededados;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

import classededados.Piloto;

public class ColecaoDeObjetos {
  private List<Piloto> listaArray = new ArrayList<>();
  private List<Piloto> listaVector = new Vector<>();
  private List<Piloto> listaLinked = new LinkedList<>();
  private Stack<Piloto> pilhaStack = new Stack<>();
  private Set<Piloto> conjuntoSet = new HashSet<>();

  public void adicionarPiloto(Piloto p) {
    listaArray.add(p);
    listaVector.add(p);
    listaLinked.add(p);
    pilhaStack.push(p);
    conjuntoSet.add(p);
  }

  // Métodos que retornam o Iterator de cada estrutura de forma padronizada
  public Iterator<Piloto> getArrayListIterator() {
    return listaArray.iterator();
  }

  public Iterator<Piloto> getVectorIterator() {
    return listaVector.iterator();
  }

  public Iterator<Piloto> getLinkedListIterator() {
    return listaLinked.iterator();
  }

  public Iterator<Piloto> getStackIterator() {
    return pilhaStack.iterator();
  }

  public Iterator<Piloto> getHashSetIterator() {
    return conjuntoSet.iterator();
  }
}
