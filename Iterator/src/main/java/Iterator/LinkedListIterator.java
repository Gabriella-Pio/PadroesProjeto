package Iterator;

import Model.Piloto;
import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListIterator implements Iterator<Piloto> {
  private LinkedList<Piloto> LinkedList;
  private Iterator<Piloto> iterator;

  public LinkedListIterator(LinkedList<Piloto> LinkedList) {
    this.LinkedList = LinkedList;
    this.iterator = LinkedList.iterator();
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
