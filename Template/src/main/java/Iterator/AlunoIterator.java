package Iterator;

import Model.Aluno;
import java.util.Iterator;
import java.util.ArrayList;

public class AlunoIterator implements Iterator<Aluno> {
    private ArrayList<Aluno> array;
    private Iterator<Aluno> iterator;

    public AlunoIterator(ArrayList<Aluno> array) {
        this.array = array;
        this.iterator = array.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Aluno next() {
        return iterator.next();
    }
}