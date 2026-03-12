package Iterator;

import Model.Piloto;

import java.util.Iterator;
import java.util.TreeSet;

public class ArvoreIterator implements Iterator<Piloto> {
  private TreeSet<Piloto> arvore;
  private Iterator<Piloto> iterator;

  public ArvoreIterator(TreeSet<Piloto> arvore) {
    this.arvore = arvore;
    this.iterator = arvore.iterator();
  }

  @Override
  public boolean hasNext() {
    return iterator.hasNext();
  }

  @Override
  public Piloto next() {
    return iterator.next();
  }
}
