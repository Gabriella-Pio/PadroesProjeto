package interfacegrafica;

import classededados.Piloto;
import persistencia.PilotosPersistencia;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Iterator;

/**
 * Interface gráfica seguindo o padrão do professor para o projeto Iterator.
 */
public class JanelaPilotos extends javax.swing.JFrame {

    public JanelaPilotos() {
        initComponents();
        setLocationRelativeTo(null); // Centraliza a janela
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jButtonArrayList = new javax.swing.JButton();
        jButtonHashSet = new javax.swing.JButton();
        jButtonStack = new javax.swing.JButton();
        jButtonLinkedList = new javax.swing.JButton();
        jButtonVector = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDados = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fórmula 1 - Padrão Iterator");

        jButtonArrayList.setText("ArrayList");
        jButtonArrayList.addActionListener(evt -> listarPilotos("ArrayList"));

        jButtonHashSet.setText("HashSet");
        jButtonHashSet.addActionListener(evt -> listarPilotos("HashSet"));

        jButtonStack.setText("Stack");
        jButtonStack.addActionListener(evt -> listarPilotos("Stack"));

        jButtonLinkedList.setText("LinkedList");
        jButtonLinkedList.addActionListener(evt -> listarPilotos("LinkedList"));

        jButtonVector.setText("Vector");
        jButtonVector.addActionListener(evt -> listarPilotos("Vector"));

        jButtonLimpar.setText("Limpar");
        jButtonLimpar.addActionListener(evt -> limparGrid());

        jTableDados.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {},
                new String[] { "Matrícula", "Nome", "País", "Idade", "Equipe", "Motor", "Pontuação" }));
        jScrollPane1.setViewportView(jTableDados);

        // Layout simplificado para demonstração
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButtonArrayList)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonVector)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonLinkedList)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonStack)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonHashSet)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50,
                                                        Short.MAX_VALUE)
                                                .addComponent(jButtonLimpar)))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonArrayList)
                                        .addComponent(jButtonVector)
                                        .addComponent(jButtonLinkedList)
                                        .addComponent(jButtonStack)
                                        .addComponent(jButtonHashSet)
                                        .addComponent(jButtonLimpar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                                .addContainerGap()));

        pack();
    }// </editor-fold>

    /**
     * O CORAÇÃO DO PADRÃO: Este método não sabe qual coleção está usando,
     * apenas percorre o Iterator.
     */
    private void imprimirDadosNaGrid(Iterator conjunto) {
        DefaultTableModel model = (DefaultTableModel) jTableDados.getModel();
        model.setNumRows(0);
        while (conjunto.hasNext()) {
            String[] linha = new String[7];
            Piloto objetoPiloto = (Piloto) conjunto.next();
            linha[0] = objetoPiloto.getMatricula();
            linha[1] = objetoPiloto.getNome();
            linha[2] = objetoPiloto.getPaisOrigem();
            linha[3] = String.valueOf(objetoPiloto.getIdade());
            linha[4] = objetoPiloto.getEquipe();
            linha[5] = objetoPiloto.getMotorCarro();
            linha[6] = String.valueOf(objetoPiloto.getPontuacao());
            model.addRow(linha);
        }
    }

    private void listarPilotos(String tipo) {
        try {
            // Caminho do seu arquivo CSV
            PilotosPersistencia persistencia = new PilotosPersistencia("./src/main/java/dados/DadosDosPilotosF1 2.csv");
            var colecao = persistencia.carregarPilotos();

            // Seleciona o Iterator conforme o botão clicado
            switch (tipo) {
                case "ArrayList" -> imprimirDadosNaGrid(colecao.getArrayListIterator());
                case "Vector" -> imprimirDadosNaGrid(colecao.getVectorIterator());
                case "LinkedList" -> imprimirDadosNaGrid(colecao.getLinkedListIterator());
                case "Stack" -> imprimirDadosNaGrid(colecao.getStackIterator());
                case "HashSet" -> imprimirDadosNaGrid(colecao.getHashSetIterator());
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Erro: " + erro.getMessage());
        }
    }

    private void limparGrid() {
        DefaultTableModel model = (DefaultTableModel) jTableDados.getModel();
        model.setNumRows(0);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new JanelaPilotos().setVisible(true));
    }

    // Declaração de variáveis
    private javax.swing.JButton jButtonArrayList;
    private javax.swing.JButton jButtonHashSet;
    private javax.swing.JButton jButtonLinkedList;
    private javax.swing.JButton jButtonStack;
    private javax.swing.JButton jButtonVector;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDados;
}