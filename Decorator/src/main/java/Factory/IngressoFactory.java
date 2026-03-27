package Factory;

import Model.*;

public class IngressoFactory {

  public static Ingresso getIngresso(Tipo tipo, String evento, double precoBase) {
    switch (tipo) {
      case MEIA:
        return new IngressoMeia(evento, precoBase);
      case IDOSO:
        return new IngressoIdoso(evento, precoBase);
      case VIP:
        return new IngressoVip(evento, precoBase);
      default:
        return new IngressoComum(evento, precoBase);
    }
  }
}
