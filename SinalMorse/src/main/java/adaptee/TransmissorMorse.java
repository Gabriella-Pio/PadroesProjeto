package adaptee;

import java.util.HashMap;
import java.util.Map;

public class TransmissorMorse implements ICodigoMorse {

  private String mensagemMorse;

  private static final Map<Character, String> TEXTO_PARA_MORSE = new HashMap<>();

  static {
    TEXTO_PARA_MORSE.put('A', ".-");
    TEXTO_PARA_MORSE.put('B', "-...");
    TEXTO_PARA_MORSE.put('C', "-.-.");
    TEXTO_PARA_MORSE.put('D', "-..");
    TEXTO_PARA_MORSE.put('E', ".");
    TEXTO_PARA_MORSE.put('F', "..-.");
    TEXTO_PARA_MORSE.put('G', "--.");
    TEXTO_PARA_MORSE.put('H', "....");
    TEXTO_PARA_MORSE.put('I', "..");
    TEXTO_PARA_MORSE.put('J', ".---");
    TEXTO_PARA_MORSE.put('K', "-.-");
    TEXTO_PARA_MORSE.put('L', ".-..");
    TEXTO_PARA_MORSE.put('M', "--");
    TEXTO_PARA_MORSE.put('N', "-.");
    TEXTO_PARA_MORSE.put('O', "---");
    TEXTO_PARA_MORSE.put('P', ".--.");
    TEXTO_PARA_MORSE.put('Q', "--.-");
    TEXTO_PARA_MORSE.put('R', ".-.");
    TEXTO_PARA_MORSE.put('S', "...");
    TEXTO_PARA_MORSE.put('T', "-");
    TEXTO_PARA_MORSE.put('U', "..-");
    TEXTO_PARA_MORSE.put('V', "...-");
    TEXTO_PARA_MORSE.put('W', ".--");
    TEXTO_PARA_MORSE.put('X', "-..-");
    TEXTO_PARA_MORSE.put('Y', "-.--");
    TEXTO_PARA_MORSE.put('Z', "--..");
    TEXTO_PARA_MORSE.put('0', "-----");
    TEXTO_PARA_MORSE.put('1', ".----");
    TEXTO_PARA_MORSE.put('2', "..---");
    TEXTO_PARA_MORSE.put('3', "...--");
    TEXTO_PARA_MORSE.put('4', "....-");
    TEXTO_PARA_MORSE.put('5', ".....");
    TEXTO_PARA_MORSE.put('6', "-....");
    TEXTO_PARA_MORSE.put('7', "--...");
    TEXTO_PARA_MORSE.put('8', "---..");
    TEXTO_PARA_MORSE.put('9', "----.");
  }

  /**
   * Cria um transmissor codificando o texto fornecido em Morse.
   * 
   * @param texto mensagem em texto plano (letras A-Z, números 0-9)
   */
  public TransmissorMorse(String texto) {
    this.mensagemMorse = codificar(texto);
  }

  /**
   * Cria um transmissor já com a string morse informada diretamente.
   * Simula receber um sinal morse de um transmissor físico.
   */
  public static TransmissorMorse comMorse(String morse) {
    TransmissorMorse t = new TransmissorMorse("");
    t.mensagemMorse = morse;
    return t;
  }

  private String codificar(String texto) {
    StringBuilder sb = new StringBuilder();
    String upper = texto.toUpperCase();
    for (int i = 0; i < upper.length(); i++) {
      char c = upper.charAt(i);
      if (c == ' ') {
        sb.append("/ ");
      } else {
        String codigo = TEXTO_PARA_MORSE.get(c);
        if (codigo != null) {
          sb.append(codigo).append(" ");
        }
      }
    }
    return sb.toString().trim();
  }

  @Override
  public String getMensagemEmMorse() {
    return mensagemMorse;
  }
}
