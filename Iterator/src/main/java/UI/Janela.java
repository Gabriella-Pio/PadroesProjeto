package UI;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Iterator.MatrizContainer;
import Model.Piloto;
import Repository.PilotosRepository;

import java.util.Iterator;

public class Janela extends javax.swing.JFrame {

    public Janela() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jButtonMatriz = new javax.swing.JButton();
        jButtonHashMap = new javax.swing.JButton();
        jButtonStack = new javax.swing.JButton();
        jButtonPriorityQueue = new javax.swing.JButton();
        jButtonTreeSet = new javax.swing.JButton();
        jButtonLinkedList = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDados = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fórmula 1");

        jButtonMatriz.setText("Matriz");
        jButtonMatriz.addActionListener(evt -> listarPilotos("Matriz"));

        jButtonStack.setText("Pilha");
        jButtonStack.addActionListener(evt -> listarPilotos("Stack"));

        jButtonPriorityQueue.setText("Fila de Prioridade");
        jButtonPriorityQueue.addActionListener(evt -> listarPilotos("PriorityQueue"));

        jButtonTreeSet.setText("Árvore");
        jButtonTreeSet.addActionListener(evt -> listarPilotos("TreeSet"));

        jButtonHashMap.setText("Tabela Hash");
        jButtonHashMap.addActionListener(evt -> listarPilotos("HashMap"));

        jButtonLinkedList.setText("LinkedList");
        jButtonLinkedList.addActionListener(evt -> listarPilotos("LinkedList"));

        jButtonLimpar.setText("Limpar");
        jButtonLimpar.addActionListener(evt -> limparGrid());

        jTableDados.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {},
                new String[] { "Matrícula", "Nome", "País de Origem", "Idade", "Equipe", "Motor", "Pontuação" }));
        jScrollPane1.setViewportView(jTableDados);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButtonMatriz)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonStack)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonPriorityQueue)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonTreeSet)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonLinkedList)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonHashMap)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50,
                                                        Short.MAX_VALUE)
                                                .addComponent(jButtonLimpar)))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonMatriz)
                                        .addComponent(jButtonStack)
                                        .addComponent(jButtonPriorityQueue)
                                        .addComponent(jButtonTreeSet)
                                        .addComponent(jButtonHashMap)
                                        .addComponent(jButtonLinkedList)
                                        .addComponent(jButtonLimpar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                                .addContainerGap()));

        pack();
    }

    private void imprimirDadosNaGrid(Iterator<Piloto> conjunto) {
        DefaultTableModel model = (DefaultTableModel) jTableDados.getModel();
        model.setNumRows(0);
        while (conjunto.hasNext()) {
            String[] linha = new String[7];
            Piloto objetoPiloto = (Piloto) conjunto.next();
            linha[0] = String.valueOf(objetoPiloto.getMatricula());
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
            // Caminho do arquivo configurado no Repositório
            PilotosRepository repository = new PilotosRepository("./src/main/java/Data/DadosDosPilotosF1.csv");
            Iterator<Piloto> iterator = null;

            // Seleciona o método de carregamento isolado para cada estrutura
            switch (tipo) {
                case "Matriz" -> iterator = repository.getMatrizIterator();
                case "Stack" -> iterator = repository.getPilhaIterator();
                case "PriorityQueue" -> iterator = repository.getFilaIterator();
                case "TreeSet" -> iterator = repository.getArvoreIterator();
                case "HashMap" -> iterator = repository.getHashMapIterator();
                case "LinkedList" -> iterator = repository.getLinkedListIterator();
            }

            if (iterator != null) {
                imprimirDadosNaGrid(iterator);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao listar: " + erro.getMessage());
        }
    }

    private void limparGrid() {
        DefaultTableModel model = (DefaultTableModel) jTableDados.getModel();
        model.setNumRows(0);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Janela().setVisible(true));
    }

    // Declaração de variáveis
    private javax.swing.JButton jButtonMatriz;
    private javax.swing.JButton jButtonStack;
    private javax.swing.JButton jButtonPriorityQueue;
    private javax.swing.JButton jButtonTreeSet;
    private javax.swing.JButton jButtonHashMap;
    private javax.swing.JButton jButtonLinkedList;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDados;
}