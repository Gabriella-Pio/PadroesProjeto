package adapter;

import adaptee.ICodigoMorse;
import target.IMensagemTexto;

import java.util.HashMap;
import java.util.Map;

public class MorseAdapter implements IMensagemTexto {

  private ICodigoMorse transmissor;

  // Tabela de decodificação
  private static final Map<String, String> MORSE_PARA_TEXTO = new HashMap<>();

  static {
    MORSE_PARA_TEXTO.put(".-", "A");
    MORSE_PARA_TEXTO.put("-...", "B");
    MORSE_PARA_TEXTO.put("-.-.", "C");
    MORSE_PARA_TEXTO.put("-..", "D");
    MORSE_PARA_TEXTO.put(".", "E");
    MORSE_PARA_TEXTO.put("..-.", "F");
    MORSE_PARA_TEXTO.put("--.", "G");
    MORSE_PARA_TEXTO.put("....", "H");
    MORSE_PARA_TEXTO.put("..", "I");
    MORSE_PARA_TEXTO.put(".---", "J");
    MORSE_PARA_TEXTO.put("-.-", "K");
    MORSE_PARA_TEXTO.put(".-..", "L");
    MORSE_PARA_TEXTO.put("--", "M");
    MORSE_PARA_TEXTO.put("-.", "N");
    MORSE_PARA_TEXTO.put("---", "O");
    MORSE_PARA_TEXTO.put(".--.", "P");
    MORSE_PARA_TEXTO.put("--.-", "Q");
    MORSE_PARA_TEXTO.put(".-.", "R");
    MORSE_PARA_TEXTO.put("...", "S");
    MORSE_PARA_TEXTO.put("-", "T");
    MORSE_PARA_TEXTO.put("..-", "U");
    MORSE_PARA_TEXTO.put("...-", "V");
    MORSE_PARA_TEXTO.put(".--", "W");
    MORSE_PARA_TEXTO.put("-..-", "X");
    MORSE_PARA_TEXTO.put("-.--", "Y");
    MORSE_PARA_TEXTO.put("--..", "Z");
    MORSE_PARA_TEXTO.put("-----", "0");
    MORSE_PARA_TEXTO.put(".----", "1");
    MORSE_PARA_TEXTO.put("..---", "2");
    MORSE_PARA_TEXTO.put("...--", "3");
    MORSE_PARA_TEXTO.put("....-", "4");
    MORSE_PARA_TEXTO.put(".....", "5");
    MORSE_PARA_TEXTO.put("-....", "6");
    MORSE_PARA_TEXTO.put("--...", "7");
    MORSE_PARA_TEXTO.put("---..", "8");
    MORSE_PARA_TEXTO.put("----.", "9");
  }

  public MorseAdapter(ICodigoMorse transmissor) {
    this.transmissor = transmissor;
  }

  /**
   * Obtém o sinal Morse do sistema legado e decodifica para texto.
   */
  @Override
  public String getMensagemEmTexto() {
    String morse = transmissor.getMensagemEmMorse();
    return decodificar(morse);
  }

  private String decodificar(String morse) {
    StringBuilder resultado = new StringBuilder();
    String[] palavras = morse.split(" / ");
    for (int i = 0; i < palavras.length; i++) {
      String[] letras = palavras[i].trim().split(" ");
      for (String codigo : letras) {
        String letra = MORSE_PARA_TEXTO.getOrDefault(codigo.trim(), "?");
        resultado.append(letra);
      }
      if (i < palavras.length - 1) {
        resultado.append(" ");
      }
    }
    return resultado.toString();
  }
}
