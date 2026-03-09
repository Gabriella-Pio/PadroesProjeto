package classededados;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Nome {

  private String primeiroNome;
  private String nomesDoMeio;
  private String sobrenome;
  private String sufixo = "";

  private static final List<String> LISTA_SUFIXOS = Arrays.asList("FILHO", "NETO", "JUNIOR", "JÚNIOR");

  private static final List<String> LISTA_PARTICULAS = Arrays.asList("da", "de", "do", "das", "dos");

  // Construtor
  public Nome(String nomeCompleto) {
    String[] partes = nomeCompleto.trim().split("\\s+");

    if (partes.length == 0)
      return;

    this.primeiroNome = partes[0];

    if (partes.length > 1) {
      int ultimoIndex = partes.length - 1;

      // Verifica se ultimo nome é um sufixo
      if (LISTA_SUFIXOS.contains(partes[ultimoIndex].toUpperCase())) {
        this.sufixo = partes[ultimoIndex];
        ultimoIndex--;
      }

      // Pega o último nome - posição do ultimoIndex
      this.sobrenome = partes[ultimoIndex];

      // Verifica se antes dele tem uma partícula
      if (ultimoIndex > 1) {
        String possivelParticula = partes[ultimoIndex - 1].toLowerCase();
        if (LISTA_PARTICULAS.contains(possivelParticula)) {
          this.sobrenome = possivelParticula + " " + this.sobrenome;
          ultimoIndex--;
        }
      }

      // O que sobrou entre o nome e o sobrenome são os nomes do meio
      StringBuilder meio = new StringBuilder();
      for (int i = 1; i < ultimoIndex; i++) {
        meio.append(partes[i]).append(" ");
      }
      this.nomesDoMeio = meio.toString().trim();
    } else {
      this.nomesDoMeio = "";
      this.sobrenome = "";
    }
  }

  // Formata o nome no padrão americano: SOBRENOME, SUFIXO, NOME
  public String formatarAmericano() {
    StringBuilder sb = new StringBuilder();

    sb.append(sobrenome.toUpperCase());

    // Se tiver sufixo, adiciona
    if (sufixo != null && !sufixo.isEmpty()) {
      sb.append(", ").append(sufixo);
    }

    // Adiciona nome e nome(s) do meio
    sb.append(", ").append(primeiroNome);

    if (nomesDoMeio != null && !nomesDoMeio.isEmpty()) {
      sb.append(" ").append(nomesDoMeio);
    }

    return sb.toString();
  }

  // Getters
  public String getPrimeiroNome() {
    return primeiroNome;
  }

  public String getNomesDoMeio() {
    return nomesDoMeio;
  }

  public String getSobrenome() {
    return sobrenome;
  }

  public String getSufixo() {
    return sufixo;
  }

  @Override
  public String toString() {
    return formatarAmericano();
  }
}
