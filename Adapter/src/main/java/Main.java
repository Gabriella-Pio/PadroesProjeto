/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gabriella
 */

import view.TelaPrincipal;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Ponto de entrada da aplicação.
 * Inicializa o Look & Feel e abre a janela principal na Event Dispatch Thread.
 */
public class Main {
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception ignored) {
    }

    SwingUtilities.invokeLater(() -> {
      TelaPrincipal tela = new TelaPrincipal();
      tela.setVisible(true);
    });
  }
}

