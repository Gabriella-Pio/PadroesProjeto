package view;

import adapter.AudioAdapter;
import adaptee.CatalogoLegado;
import target.FaixaMusical;
import target.IFaixaMusical;
import javax.swing.JOptionPane;

public class PlayerTela extends javax.swing.JFrame {

  private IFaixaMusical faixaAtual = null;

  public PlayerTela() {
    initComponents();
    setLocationRelativeTo(null);
  }

  @SuppressWarnings("unchecked")
  private void initComponents() {
    jLabel1 = new javax.swing.JLabel();
    jLabelTitulo = new javax.swing.JLabel();
    jLabelArtista = new javax.swing.JLabel();
    jLabelDuracao = new javax.swing.JLabel();
    jTextFieldTitulo = new javax.swing.JTextField();
    jTextFieldArtista = new javax.swing.JTextField();
    jTextFieldDuracao = new javax.swing.JTextField();
    jLabelLegado = new javax.swing.JLabel();
    jTextFieldLegado = new javax.swing.JTextField();
    jButtonDireto = new javax.swing.JButton();
    jButtonAdapter = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    jTextAreaResultado = new javax.swing.JTextArea();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("PLAYER MUSICAL");

    jLabel1.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
    jLabel1.setText("PLAYER MUSICAL");

    jLabelTitulo.setText("Título:");
    jLabelArtista.setText("Artista:");
    jLabelDuracao.setText("Duração:");
    jLabelLegado.setText("Formato legado:");

    jTextFieldTitulo.setToolTipText("Ex: Let It Be");
    jTextFieldArtista.setToolTipText("Ex: The Beatles");
    jTextFieldDuracao.setToolTipText("Ex: 03:50");
    jTextFieldLegado.setToolTipText("Ex: The Beatles - Let It Be (03:50)");
    jTextFieldLegado.setText("The Beatles - Let It Be (03:50)");

    jButtonDireto.setText("Faixa Direta (sem Adapter)");
    jButtonDireto.addActionListener(evt -> usarFaixaDireta());

    jButtonAdapter.setText("Catálogo Legado (via Adapter)");
    jButtonAdapter.addActionListener(evt -> usarCatalogoAdapter());

    jTextAreaResultado.setColumns(20);
    jTextAreaResultado.setRows(6);
    jTextAreaResultado.setEditable(false);
    jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("RESULTADO"));
    jScrollPane1.setViewportView(jTextAreaResultado);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);

    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18)
                        .addComponent(jLabelArtista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldArtista, javax.swing.GroupLayout.PREFERRED_SIZE, 160,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18)
                        .addComponent(jLabelDuracao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDuracao, javax.swing.GroupLayout.PREFERRED_SIZE, 70,
                            javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelLegado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldLegado, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonDireto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAdapter))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE))
                .addContainerGap()));

    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTitulo)
                    .addComponent(jTextFieldTitulo, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelArtista)
                    .addComponent(jTextFieldArtista, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDuracao)
                    .addComponent(jTextFieldDuracao, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLegado)
                    .addComponent(jTextFieldLegado, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDireto)
                    .addComponent(jButtonAdapter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addContainerGap()));

    pack();
  }

  /** Usa FaixaMusical diretamente — SEM Adapter. */
  private void usarFaixaDireta() {
    String titulo = jTextFieldTitulo.getText().trim();
    String artista = jTextFieldArtista.getText().trim();
    String duracao = jTextFieldDuracao.getText().trim();
    if (titulo.isEmpty() || artista.isEmpty() || duracao.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Preencha Título, Artista e Duração.");
      return;
    }
    faixaAtual = new FaixaMusical(titulo, artista, duracao);
    jTextAreaResultado.setText(
        "[FaixaMusical nativa - sem Adapter]\n\n" +
            "Titulo  : " + faixaAtual.getTitulo() + "\n" +
            "Artista : " + faixaAtual.getArtista() + "\n" +
            "Duracao : " + faixaAtual.getDuracao());
  }

  private void usarCatalogoAdapter() {
    String legado = jTextFieldLegado.getText().trim();
    if (legado.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Preencha o campo de formato legado.\nEx: The Beatles - Let It Be (03:50)");
      return;
    }
    String[] partes = legado.split(" - ", 2);
    String artista = partes.length > 0 ? partes[0].trim() : "?";
    String restoStr = partes.length > 1 ? partes[1].trim() : legado;
    int paren = restoStr.lastIndexOf("(");
    String titulo = paren > 0 ? restoStr.substring(0, paren).trim() : restoStr;
    String duracao = paren > 0 ? restoStr.substring(paren + 1).replace(")", "").trim() : "--:--";

    CatalogoLegado catalogo = new CatalogoLegado(artista, titulo, duracao);
    faixaAtual = new AudioAdapter(catalogo);

    jTextAreaResultado.setText(
        "[AudioAdapter - CatalogoLegado adaptado para IFaixaMusical]\n\n" +
            "Formato legado recebido:\n  \"" + catalogo.getDadosFormatados() + "\"\n\n" +
            "Apos adaptacao:\n" +
            "Titulo  : " + faixaAtual.getTitulo() + "\n" +
            "Artista : " + faixaAtual.getArtista() + "\n" +
            "Duracao : " + faixaAtual.getDuracao());
  }

  public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(() -> new PlayerTela().setVisible(true));
  }

  // Declaração de variáveis
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabelTitulo;
  private javax.swing.JLabel jLabelArtista;
  private javax.swing.JLabel jLabelDuracao;
  private javax.swing.JLabel jLabelLegado;
  private javax.swing.JTextField jTextFieldTitulo;
  private javax.swing.JTextField jTextFieldArtista;
  private javax.swing.JTextField jTextFieldDuracao;
  private javax.swing.JTextField jTextFieldLegado;
  private javax.swing.JButton jButtonDireto;
  private javax.swing.JButton jButtonAdapter;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextArea jTextAreaResultado;
}
