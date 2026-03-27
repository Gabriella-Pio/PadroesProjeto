package Iterator;

import Model.Ingresso;
import java.util.Iterator;
import java.util.ArrayList;

public class VendasIterator implements Iterator<Ingresso> {
    private ArrayList<Ingresso> array;
    private Iterator<Ingresso> iterator;

    public VendasIterator(ArrayList<Ingresso> array) {
        this.array = array;
        this.iterator = array.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Ingresso next() {
        return iterator.next();
    }
}