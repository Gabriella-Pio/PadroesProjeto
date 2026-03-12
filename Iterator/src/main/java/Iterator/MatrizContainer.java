package Iterator;

import Model.Piloto;
import java.util.Iterator;

public class MatrizContainer {
  private Piloto[][] matriz;

  public MatrizContainer(Piloto[][] matriz) {
    this.matriz = matriz;
  }

  public Iterator<Piloto> getMatriz() {
    return new MatrizIterator(this.matriz);
  }
}
