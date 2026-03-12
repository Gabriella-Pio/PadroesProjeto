package Iterator;

import java.util.Iterator;
import java.util.Queue;

import Model.Piloto;

public class FilaIterator implements Iterator<Piloto> {
  private Iterator<Piloto> iterator;
  private Queue<Piloto> fila;

  public FilaIterator(Queue<Piloto> fila) {
    this.fila = fila;
    this.iterator = fila.iterator();
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
