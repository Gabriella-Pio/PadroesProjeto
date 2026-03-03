import services.SistemaMultas;
import ui.TelaRadar;
import ui.TelaCentral;
import javax.swing.SwingUtilities;

public class Main {
  public static void main(String[] args) {
    System.out.println("=".repeat(70));
    System.out.println("Sistema de Multas - Padrão Singleton");
    System.out.println("=".repeat(70));
    System.out.println();

    // Obtém múltiplas referências
    SistemaMultas s1 = SistemaMultas.getInstance();
    SistemaMultas s2 = SistemaMultas.getInstance();
    SistemaMultas s3 = SistemaMultas.getInstance();

    System.out.println("Referência 1: " + s1.hashCode());
    System.out.println("Referência 2: " + s2.hashCode());
    System.out.println("Referência 3: " + s3.hashCode());
    System.out.println();

    System.out.println("Todas são iguais? " + (s1 == s2 && s2 == s3));
    System.out.println();
    System.out.println("=".repeat(70));
    System.out.println();

    // Abre as janelas
    SwingUtilities.invokeLater(() -> {
      System.out.println("Abrindo interfaces gráficas...");
      System.out.println();

      // Abre 2 radares
      TelaRadar radar1 = new TelaRadar();
      radar1.setVisible(true);

      TelaRadar radar2 = new TelaRadar();
      radar2.setVisible(true);

      // Abre a central
      TelaCentral central = new TelaCentral();
      central.setVisible(true);

      System.out.println();
      System.out.println("=".repeat(70));
      System.out.println("Sistema Iniciado");
      System.out.println();
      System.out.println("=".repeat(70));
    });
  }
}
