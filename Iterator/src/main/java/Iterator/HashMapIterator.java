package Iterator;

import Model.Piloto;
import java.util.Iterator;
import java.util.HashMap;

public class HashMapIterator implements Iterator<Piloto> {
  private HashMap<String, Piloto> HashMap;
  private Iterator<Piloto> iterator;

  public HashMapIterator(HashMap<String, Piloto> HashMap) {
    this.HashMap = HashMap;
    this.iterator = HashMap.values().iterator();
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
