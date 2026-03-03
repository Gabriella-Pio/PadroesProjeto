package ui;

import models.Multa;
import services.SistemaMultas;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Tela Central - Visualiza TODAS as multas cadastradas.
 * Demonstra que todos os radares acessam o MESMO Singleton,
 * pois todas as multas aparecem aqui, independente de qual radar cadastrou.
 */
public class TelaCentral extends JFrame {
  private SistemaMultas sistema;
  private JTable tblMultas;
  private DefaultTableModel tableModel;
  private JLabel lblTotalMultas;
  private JLabel lblValorTotal;
  private JLabel lblHashCode;

  public TelaCentral() {
    // Obtém a instância ÚNICA do Singleton
    sistema = SistemaMultas.getInstance();

    configurarJanela();
    criarInterface();
    atualizarRelatorio();

    System.out.println("Central de Controle iniciada - Instância Singleton: " + sistema.hashCode());
  }

  private void configurarJanela() {
    setTitle("CENTRAL DE CONTROLE - Todas as Multas");
    setSize(900, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(550, 50); // Posiciona à direita dos radares
  }

  private void criarInterface() {
    setLayout(new BorderLayout(10, 10));

    // Painel de título
    JPanel panelTitulo = new JPanel(new BorderLayout());
    panelTitulo.setBackground(new Color(41, 128, 185)); // Azul para central
    panelTitulo.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    JLabel lblTitulo = new JLabel("CENTRAL DE CONTROLE");
    lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
    lblTitulo.setForeground(Color.WHITE);

    JLabel lblSubtitulo = new JLabel("Sistema Único - Padrão Singleton");
    lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
    lblSubtitulo.setForeground(new Color(236, 240, 241));

    JPanel titulos = new JPanel(new GridLayout(2, 1));
    titulos.setOpaque(false);
    titulos.add(lblTitulo);
    titulos.add(lblSubtitulo);

    panelTitulo.add(titulos, BorderLayout.WEST);

    add(panelTitulo, BorderLayout.NORTH);

    // Painel central - Tabela
    JPanel panelCentral = new JPanel(new BorderLayout());
    panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Botão de atualizar
    JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
    panelBotoes.setBackground(new Color(236, 240, 241));

    JButton btnAtualizar = new JButton("ATUALIZAR RELATÓRIO");
    btnAtualizar.setFont(new Font("Arial", Font.BOLD, 14));
    btnAtualizar.setBackground(new Color(39, 174, 96));
    btnAtualizar.setForeground(Color.WHITE);
    btnAtualizar.setFocusPainted(false);
    btnAtualizar.addActionListener(e -> atualizarRelatorio());

    JButton btnLimpar = new JButton("LIMPAR TODAS");
    btnLimpar.setFont(new Font("Arial", Font.BOLD, 14));
    btnLimpar.setBackground(new Color(192, 57, 43));
    btnLimpar.setForeground(Color.WHITE);
    btnLimpar.setFocusPainted(false);
    btnLimpar.addActionListener(e -> limparTodas());

    JButton btnNovoRadar = new JButton("REGISTRAR EM NOVO RADAR");
    btnNovoRadar.setFont(new Font("Arial", Font.BOLD, 14));
    btnNovoRadar.setBackground(new Color(41, 128, 185)); 
    btnNovoRadar.setForeground(Color.WHITE);
    btnNovoRadar.setFocusPainted(false);

    btnNovoRadar.addActionListener(e -> {
      TelaRadar novoRadar = new TelaRadar();
      novoRadar.setVisible(true);
    });

    panelBotoes.add(btnNovoRadar);
    panelBotoes.add(btnAtualizar);
    panelBotoes.add(btnLimpar);

    panelCentral.add(panelBotoes, BorderLayout.NORTH);

    // Tabela
    String[] colunas = { "Placa", "Infração", "Valor", "Data", "Local", "Origem" };
    tableModel = new DefaultTableModel(colunas, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    tblMultas = new JTable(tableModel);
    tblMultas.getTableHeader().setBackground(new Color(52, 73, 94));
    tblMultas.getTableHeader().setForeground(Color.WHITE);
    tblMultas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
    tblMultas.setRowHeight(30);
    tblMultas.setFont(new Font("Arial", Font.PLAIN, 12));

    // Ajusta largura das colunas
    tblMultas.getColumnModel().getColumn(0).setPreferredWidth(100); // Placa
    tblMultas.getColumnModel().getColumn(1).setPreferredWidth(250); // Infração
    tblMultas.getColumnModel().getColumn(2).setPreferredWidth(100); // Valor
    tblMultas.getColumnModel().getColumn(3).setPreferredWidth(100); // Data
    tblMultas.getColumnModel().getColumn(4).setPreferredWidth(300); // Local

    JScrollPane scrollPane = new JScrollPane(tblMultas);
    panelCentral.add(scrollPane, BorderLayout.CENTER);

    add(panelCentral, BorderLayout.CENTER);

    // Painel inferior - Estatísticas e Singleton
    JPanel panelInferior = new JPanel(new BorderLayout());

    // Estatísticas
    JPanel panelEstatisticas = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
    panelEstatisticas.setBackground(new Color(236, 240, 241));

    lblTotalMultas = new JLabel("Total de Multas: 0");
    lblTotalMultas.setFont(new Font("Arial", Font.BOLD, 16));

    lblValorTotal = new JLabel("Valor Total: R$ 0,00");
    lblValorTotal.setFont(new Font("Arial", Font.BOLD, 16));
    lblValorTotal.setForeground(new Color(192, 57, 43));

    panelEstatisticas.add(lblTotalMultas);
    panelEstatisticas.add(new JLabel("|"));
    panelEstatisticas.add(lblValorTotal);

    // Singleton info
    JPanel panelSingleton = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panelSingleton.setBackground(new Color(241, 196, 15));
    panelSingleton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JLabel lblSingletonLabel = new JLabel("Instância Singleton: ");
    lblSingletonLabel.setFont(new Font("Arial", Font.BOLD, 13));

    lblHashCode = new JLabel(String.valueOf(sistema.hashCode()));
    lblHashCode.setFont(new Font("Monospaced", Font.BOLD, 16));
    lblHashCode.setForeground(new Color(41, 128, 185));

    panelSingleton.add(lblSingletonLabel);
    panelSingleton.add(lblHashCode);

    panelInferior.add(panelEstatisticas, BorderLayout.CENTER);
    panelInferior.add(panelSingleton, BorderLayout.SOUTH);

    add(panelInferior, BorderLayout.SOUTH);
  }

  private void atualizarRelatorio() {
    // Limpa a tabela
    tableModel.setRowCount(0);

    // Obtém TODAS as multas do Singleton
    List<Multa> multas = sistema.listarTodas();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Preenche a tabela
    for (Multa m : multas) {
      Object[] row = {
          m.getPlaca(),
          m.getTipoInfracao(),
          String.format("R$ %.2f", m.getValor()),
          m.getData().format(formatter),
          m.getLocal(),
          m.getOrigem()
      };
      tableModel.addRow(row);
    }

    // Atualiza estatísticas
    lblTotalMultas.setText("Total de Multas: " + sistema.getTotalMultas());
    lblValorTotal.setText(String.format("Valor Total: R$ %.2f", sistema.getValorTotal()));

    System.out.println("Relatório atualizado - " + sistema.getTotalMultas() + " multas encontradas");
  }

  private void limparTodas() {
    int resposta = JOptionPane.showConfirmDialog(this,
        "Tem certeza que deseja limpar todas as multas?",
        "Confirmar Limpeza",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE);

    if (resposta == JOptionPane.YES_OPTION) {
      sistema.limparTodas();
      atualizarRelatorio();
      JOptionPane.showMessageDialog(this,
          "Todas as multas foram removidas!",
          "Central de Controle",
          JOptionPane.INFORMATION_MESSAGE);
    }
  }
}
