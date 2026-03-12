package Iterator;

import Model.Piloto;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrizIterator implements Iterator<Piloto> {
  private Piloto[][] matriz;
  private int row;
  private int col;

  public MatrizIterator(Piloto[][] matriz) {
    this.matriz = matriz;
    this.row = 0;
    this.col = 0;
  }

  @Override
  public boolean hasNext() {
    while (row < matriz.length) {
      if (col < matriz[row].length) {
        return true;
      } else {
        row++;
        col = 0;
      }
    }
    return false;
  }

  @Override
  public Piloto next() {
    if (!hasNext()) {
      throw new NoSuchElementException("Não há mais elementos na matriz.");
    }
    return matriz[row][col++];
  }
}