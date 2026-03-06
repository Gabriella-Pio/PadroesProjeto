package view;

import adapter.MorseAdapter;
import adaptee.TransmissorMorse;
import target.IMensagemTexto;
import target.MensagemTexto;
import javax.swing.JOptionPane;

public class MorseTela extends javax.swing.JFrame {

  private IMensagemTexto mensagem = null;

  public MorseTela() {
    initComponents();
    setLocationRelativeTo(null);
  }

  @SuppressWarnings("unchecked")
  private void initComponents() {
    jLabel1 = new javax.swing.JLabel();
    jLabelEntrada = new javax.swing.JLabel();
    jTextFieldEntrada = new javax.swing.JTextField();
    jScrollPaneMorse = new javax.swing.JScrollPane();
    jTextAreaMorse = new javax.swing.JTextArea();
    jButtonTexto = new javax.swing.JButton();
    jButtonMorse = new javax.swing.JButton();
    jScrollPaneResultado = new javax.swing.JScrollPane();
    jTextAreaResultado = new javax.swing.JTextArea();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("SINAL MORSE");

    jLabel1.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
    jLabel1.setText("SINAL MORSE");

    jLabelEntrada.setText("Mensagem:");

    jTextAreaMorse.setColumns(20);
    jTextAreaMorse.setRows(3);
    jTextAreaMorse.setEditable(false);
    jScrollPaneMorse.setBorder(javax.swing.BorderFactory.createTitledBorder("CÓDIGO MORSE"));
    jScrollPaneMorse.setViewportView(jTextAreaMorse);

    jButtonTexto.setText("Enviar texto");
    jButtonTexto.addActionListener(evt -> usarTextoNativo());

    jButtonMorse.setText("Enviar - Rádio Morse");
    jButtonMorse.addActionListener(evt -> usarMorseAdapter());

    jTextAreaResultado.setColumns(20);
    jTextAreaResultado.setRows(5);
    jTextAreaResultado.setEditable(false);
    jScrollPaneResultado.setBorder(javax.swing.BorderFactory.createTitledBorder("RESULTADO"));
    jScrollPaneResultado.setViewportView(jTextAreaResultado);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelEntrada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 280,
                            javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPaneMorse, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonTexto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonMorse))
                    .addComponent(jScrollPaneResultado, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEntrada)
                    .addComponent(jTextFieldEntrada, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneMorse, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonTexto)
                    .addComponent(jButtonMorse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneResultado, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap()));

    pack();
  }

  /** Usa MensagemTexto diretamente — SEM Adapter. */
  private void usarTextoNativo() {
    String entrada = jTextFieldEntrada.getText().trim();
    if (entrada.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Digite uma mensagem.");
      return;
    }
    mensagem = new MensagemTexto(entrada);
    jTextAreaMorse.setText("(Texto enviado diretamente - sem código Morse)");
    jTextAreaResultado.setText(
        "[MensagemTexto nativa]\n\n" +
            "Entrada : \"" + entrada + "\"\n" +
            "Saída   : \"" + mensagem.getMensagemEmTexto() + "\"");
  }

  /**
   * Codifica em Morse e usa MorseAdapter para decodificar
   */
  private void usarMorseAdapter() {
    String entrada = jTextFieldEntrada.getText().trim();
    if (entrada.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Digite uma mensagem.");
      return;
    }
    TransmissorMorse transmissor = new TransmissorMorse(entrada);
    String codigoMorse = transmissor.getMensagemEmMorse();
    jTextAreaMorse.setText(codigoMorse);

    mensagem = new MorseAdapter(transmissor);
    jTextAreaResultado.setText(
        "[TransmissorMorse - MorseAdapter]\n\n" +
            "Entrada (texto) : \"" + entrada.toUpperCase() + "\"\n" +
            "Morse gerado    : " + codigoMorse + "\n" +
            "Decodificado    : \"" + mensagem.getMensagemEmTexto() + "\"");
  }

  public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(() -> new MorseTela().setVisible(true));
  }

  // Declaração de variáveis
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabelEntrada;
  private javax.swing.JTextField jTextFieldEntrada;
  private javax.swing.JScrollPane jScrollPaneMorse;
  private javax.swing.JTextArea jTextAreaMorse;
  private javax.swing.JButton jButtonTexto;
  private javax.swing.JButton jButtonMorse;
  private javax.swing.JScrollPane jScrollPaneResultado;
  private javax.swing.JTextArea jTextAreaResultado;
}
