package Iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

import Model.Piloto;

public class PilhaIterator implements Iterator<Piloto> {
  // private Iterator<Piloto> iterator;
  private Stack<Piloto> pilha;
  private int index;

  public PilhaIterator(Stack<Piloto> pilha) {
    this.pilha = pilha;
    this.index = pilha.size() - 1; // Começa do topo da pilha
    // this.iterator = pilha.iterator();
  }

  @Override
  public boolean hasNext() {
    return index >= 0;
    // return iterator.hasNext();
  }

  @Override
  public Piloto next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    return pilha.get(index--); // Retorna o elemento atual e decrementa o índice
    // return iterator.next();
  }
}
