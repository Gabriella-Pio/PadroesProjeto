package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Model.Aluno;
import Repository.*;

import java.awt.*;
import java.util.Iterator;

public class Janela extends javax.swing.JFrame {

  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable jTableGridAlunos;
  private javax.swing.JComboBox<String> jComboBoxOrdenacao;
  private javax.swing.JLabel jLabelInstrucao;

  public Janela() {
    initComponents();
    this.setTitle("LISTA DE ALUNOS - UniBrasilTI");
    this.setLocationRelativeTo(null); // Centraliza na tela
  }

  private void initComponents() {
    jScrollPane1 = new javax.swing.JScrollPane();
    jTableGridAlunos = new javax.swing.JTable();
    jLabelInstrucao = new javax.swing.JLabel("Selecione a Ordenação:");

    String[] opcoes = {
        "Por Nome", "Por Sobrenome", "Por Situação e Nome",
        "Por Curso e Nome", "Por Ênfase e Nome",
        "Por Curso, Ênfase e Nome", "Por Ênfase, Curso e Nome",
        "Situação, Ênfase, Curso e Nome"
    };
    jComboBoxOrdenacao = new javax.swing.JComboBox<>(opcoes);

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    // Configuração básica da Tabela
    jTableGridAlunos.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][] {},
        new String[] { "NOME", "CURSO", "SITUAÇÃO", "ÊNFASE" }));
    jScrollPane1.setViewportView(jTableGridAlunos);

    // Evento de troca no ComboBox
    jComboBoxOrdenacao.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jComboBoxOrdenacaoActionPerformed(evt);
      }
    });

    // Layout simplificado (FlowLayout no topo + Tabela no centro)
    JPanel painelTopo = new JPanel(new FlowLayout(FlowLayout.LEFT));
    painelTopo.add(jLabelInstrucao);
    painelTopo.add(jComboBoxOrdenacao);

    this.getContentPane().setLayout(new BorderLayout());
    this.getContentPane().add(painelTopo, BorderLayout.NORTH);
    this.getContentPane().add(jScrollPane1, BorderLayout.CENTER);

    this.setSize(850, 550);
  }

  private void imprimirNaGrid(Iterator<Aluno> dados) {
    try {
      DefaultTableModel model = (DefaultTableModel) jTableGridAlunos.getModel();
      model.setNumRows(0);
      while (dados.hasNext()) {
        Aluno obj = dados.next();
        String[] linha = new String[4];
        linha[0] = obj.getNome().formatarAmericano();
        linha[1] = obj.getCurso().getNome();
        linha[2] = obj.getSituacao() ? "Sim" : "Não";
        linha[3] = obj.getEnfase();
        model.addRow(linha);
      }
    } catch (Exception erro) {
      JOptionPane.showMessageDialog(rootPane, "Erro na grid: " + erro.getMessage());
    }
  }

  private void jComboBoxOrdenacaoActionPerformed(java.awt.event.ActionEvent evt) {
    try {
      String caminho = "./src/dados/RelatorioDasEnfases.csv";
      AlunosTemplateMethod ordenador = null;
      int opcao = jComboBoxOrdenacao.getSelectedIndex();

      switch (opcao) {
        case 0:
          ordenador = new OrdenadoPorNome(caminho);
          break;
        case 1:
          ordenador = new OrdenadoPorSobrenome(caminho);
          break;
        case 2:
          ordenador = new OrdenadoPorSituacaoNome(caminho);
          break;
        case 3:
          ordenador = new OrdenadoPorCursoNome(caminho);
          break;
        case 4:
          ordenador = new OrdenadoPorEnfaseNome(caminho);
          break;
        case 5:
          ordenador = new OrdenadoPorCursoEnfaseNome(caminho);
          break;
        case 6:
          ordenador = new OrdenadoPorEnfaseCursoNome(caminho);
          break;
        case 7:
          ordenador = new OrdenadoPorSituacaoEnfaseCursoNome(caminho);
          break;
      }

      if (ordenador != null) {
        imprimirNaGrid(ordenador.listarAlunos());
      }
    } catch (Exception erro) {
      JOptionPane.showMessageDialog(rootPane, "Erro: " + erro.getMessage());
    }
  }

  public static void main(String args[]) {
    // Define o Look and Feel Nimbus (o padrão clássico do professor)
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (Exception ex) {
    }

    java.awt.EventQueue.invokeLater(() -> {
      new Janela().setVisible(true);
    });
  }
}