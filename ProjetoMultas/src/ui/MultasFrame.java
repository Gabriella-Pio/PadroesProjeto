// package ui;

// import models.Multa;
// import services.SistemaMultas;
// import javax.swing.*;
// import javax.swing.table.DefaultTableModel;
// import java.awt.*;
// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// /**
//  * Interface gráfica principal do Sistema de Multas.
//  * Demonstra o padrão Singleton através de uma UI funcional.
//  */
// public class MultasFrame extends JFrame {
//   // Referência ao Singleton
//   private SistemaMultas sistema;

//   // Componentes da interface
//   private JTextField txtPlaca;
//   private JComboBox<String> cmbTipoInfracao;
//   private JTextField txtValor;
//   private JTextField txtData;
//   private JTextField txtLocal;
//   private JTable tblMultas;
//   private DefaultTableModel tableModel;
//   private JLabel lblTotalMultas;
//   private JLabel lblValorTotal;
//   private JTextField txtFiltroPlaca;
//   private JComboBox<String> cmbFiltroTipo;

//   // Mapa de infrações com valores padrão
//   private Map<String, Double> valoresPadrao;

//   public MultasFrame() {
//     // Obtém a instância única do sistema
//     sistema = SistemaMultas.getInstance();

//     // Inicializa valores padrão das infrações
//     inicializarValoresPadrao();

//     // Configurações da janela
//     setTitle("Sistema de Multas de Trânsito - Padrão Singleton");
//     setSize(1000, 700);
//     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//     setLocationRelativeTo(null);

//     // Cria o layout principal
//     criarInterface();

//     // Exibe informação do Singleton no console
//     System.out.println("\n=== DEMONSTRAÇÃO DO SINGLETON ===");
//     System.out.println("Instância do sistema na UI: " + sistema.hashCode());

//     setVisible(true);
//   }

//   private void inicializarValoresPadrao() {
//     valoresPadrao = new HashMap<>();
//     valoresPadrao.put("Excesso de Velocidade", 195.23);
//     valoresPadrao.put("Estacionamento Irregular", 130.16);
//     valoresPadrao.put("Avanço de Sinal Vermelho", 293.47);
//     valoresPadrao.put("Dirigir sem CNH", 880.41);
//     valoresPadrao.put("Uso de Celular ao Volante", 293.47);
//     valoresPadrao.put("Falta de Cinto de Segurança", 195.23);
//     valoresPadrao.put("Ultrapassagem Indevida", 195.23);
//     valoresPadrao.put("Outras Infrações", 0.0);
//   }

//   private void criarInterface() {
//     setLayout(new BorderLayout(10, 10));

//     // Painel superior - Título
//     JPanel panelTitulo = criarPanelTitulo();
//     add(panelTitulo, BorderLayout.NORTH);

//     // Painel central - Cadastro e Tabela
//     JPanel panelCentro = new JPanel(new GridLayout(1, 2, 10, 0));
//     panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

//     JPanel panelCadastro = criarPanelCadastro();
//     JPanel panelVisualizacao = criarPanelVisualizacao();

//     panelCentro.add(panelCadastro);
//     panelCentro.add(panelVisualizacao);

//     add(panelCentro, BorderLayout.CENTER);

//     // Painel inferior - Estatísticas
//     JPanel panelEstatisticas = criarPanelEstatisticas();
//     add(panelEstatisticas, BorderLayout.SOUTH);
//   }

//   private JPanel criarPanelTitulo() {
//     JPanel panel = new JPanel();
//     panel.setBackground(new Color(41, 128, 185));
//     panel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

//     JLabel lblTitulo = new JLabel("🚦 Sistema de Multas de Trânsito");
//     lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
//     lblTitulo.setForeground(Color.WHITE);

//     JLabel lblSubtitulo = new JLabel("Padrão Singleton - Controle Centralizado");
//     lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
//     lblSubtitulo.setForeground(new Color(236, 240, 241));

//     panel.setLayout(new GridLayout(2, 1));
//     panel.add(lblTitulo);
//     panel.add(lblSubtitulo);

//     return panel;
//   }

//   private JPanel criarPanelCadastro() {
//     JPanel panel = new JPanel();
//     panel.setLayout(new BorderLayout());
//     panel.setBorder(BorderFactory.createTitledBorder("Cadastro de Multa"));

//     JPanel formPanel = new JPanel(new GridBagLayout());
//     GridBagConstraints gbc = new GridBagConstraints();
//     gbc.fill = GridBagConstraints.HORIZONTAL;
//     gbc.insets = new Insets(5, 5, 5, 5);

//     // Placa
//     gbc.gridx = 0;
//     gbc.gridy = 0;
//     formPanel.add(new JLabel("Placa:"), gbc);
//     gbc.gridx = 1;
//     txtPlaca = new JTextField(15);
//     formPanel.add(txtPlaca, gbc);

//     // Tipo de Infração
//     gbc.gridx = 0;
//     gbc.gridy = 1;
//     formPanel.add(new JLabel("Tipo de Infração:"), gbc);
//     gbc.gridx = 1;
//     String[] tiposInfracao = {
//         "Excesso de Velocidade",
//         "Estacionamento Irregular",
//         "Avanço de Sinal Vermelho",
//         "Dirigir sem CNH",
//         "Uso de Celular ao Volante",
//         "Falta de Cinto de Segurança",
//         "Ultrapassagem Indevida",
//         "Outras Infrações"
//     };
//     cmbTipoInfracao = new JComboBox<>(tiposInfracao);
//     cmbTipoInfracao.addActionListener(e -> atualizarValorPadrao());
//     formPanel.add(cmbTipoInfracao, gbc);

//     // Valor
//     gbc.gridx = 0;
//     gbc.gridy = 2;
//     formPanel.add(new JLabel("Valor (R$):"), gbc);
//     gbc.gridx = 1;
//     txtValor = new JTextField(15);
//     txtValor.setText("195.23");
//     formPanel.add(txtValor, gbc);

//     // Data
//     gbc.gridx = 0;
//     gbc.gridy = 3;
//     formPanel.add(new JLabel("Data (dd/MM/yyyy):"), gbc);
//     gbc.gridx = 1;
//     txtData = new JTextField(15);
//     txtData.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//     formPanel.add(txtData, gbc);

//     // Local
//     gbc.gridx = 0;
//     gbc.gridy = 4;
//     formPanel.add(new JLabel("Local:"), gbc);
//     gbc.gridx = 1;
//     txtLocal = new JTextField(15);
//     formPanel.add(txtLocal, gbc);

//     // Botão Cadastrar
//     gbc.gridx = 0;
//     gbc.gridy = 5;
//     gbc.gridwidth = 2;
//     JButton btnCadastrar = new JButton("Cadastrar Multa");
//     btnCadastrar.setBackground(new Color(39, 174, 96));
//     btnCadastrar.setForeground(Color.WHITE);
//     btnCadastrar.setFont(new Font("Arial", Font.BOLD, 14));
//     btnCadastrar.addActionListener(e -> cadastrarMulta());
//     formPanel.add(btnCadastrar, gbc);

//     // Informação do Singleton
//     gbc.gridy = 6;
//     JPanel infoPanel = new JPanel();
//     infoPanel.setBackground(new Color(241, 196, 15));
//     infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//     JLabel lblInfo = new JLabel("<html><b>Singleton:</b> Instância " + sistema.hashCode() + "</html>");
//     infoPanel.add(lblInfo);
//     formPanel.add(infoPanel, gbc);

//     panel.add(formPanel, BorderLayout.CENTER);

//     return panel;
//   }

//   private JPanel criarPanelVisualizacao() {
//     JPanel panel = new JPanel(new BorderLayout());
//     panel.setBorder(BorderFactory.createTitledBorder("Multas Cadastradas"));

//     // Painel de filtros
//     JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT));
//     panelFiltros.add(new JLabel("Filtrar por Placa:"));
//     txtFiltroPlaca = new JTextField(10);
//     panelFiltros.add(txtFiltroPlaca);

//     panelFiltros.add(new JLabel("Tipo:"));
//     String[] tiposFiltro = { "Todas", "Excesso de Velocidade", "Estacionamento Irregular",
//         "Avanço de Sinal Vermelho", "Dirigir sem CNH",
//         "Uso de Celular ao Volante", "Falta de Cinto de Segurança",
//         "Ultrapassagem Indevida", "Outras Infrações" };
//     cmbFiltroTipo = new JComboBox<>(tiposFiltro);
//     panelFiltros.add(cmbFiltroTipo);

//     JButton btnFiltrar = new JButton("Filtrar");
//     btnFiltrar.addActionListener(e -> filtrarMultas());
//     panelFiltros.add(btnFiltrar);

//     JButton btnLimparFiltro = new JButton("Limpar");
//     btnLimparFiltro.addActionListener(e -> limparFiltros());
//     panelFiltros.add(btnLimparFiltro);

//     panel.add(panelFiltros, BorderLayout.NORTH);

//     // Tabela
//     String[] colunas = { "Placa", "Infração", "Valor", "Data", "Local" };
//     tableModel = new DefaultTableModel(colunas, 0) {
//       @Override
//       public boolean isCellEditable(int row, int column) {
//         return false;
//       }
//     };
//     tblMultas = new JTable(tableModel);
//     tblMultas.getTableHeader().setBackground(new Color(52, 73, 94));
//     tblMultas.getTableHeader().setForeground(Color.WHITE);
//     tblMultas.setRowHeight(25);

//     JScrollPane scrollPane = new JScrollPane(tblMultas);
//     panel.add(scrollPane, BorderLayout.CENTER);

//     return panel;
//   }

//   private JPanel criarPanelEstatisticas() {
//     JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
//     panel.setBackground(new Color(236, 240, 241));
//     panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

//     lblTotalMultas = new JLabel("Total de Multas: 0");
//     lblTotalMultas.setFont(new Font("Arial", Font.BOLD, 16));

//     lblValorTotal = new JLabel("Valor Total: R$ 0,00");
//     lblValorTotal.setFont(new Font("Arial", Font.BOLD, 16));
//     lblValorTotal.setForeground(new Color(192, 57, 43));

//     panel.add(lblTotalMultas);
//     panel.add(new JLabel("|"));
//     panel.add(lblValorTotal);

//     return panel;
//   }

//   private void atualizarValorPadrao() {
//     String tipoSelecionado = (String) cmbTipoInfracao.getSelectedItem();
//     Double valorPadrao = valoresPadrao.get(tipoSelecionado);
//     if (valorPadrao != null && valorPadrao > 0) {
//       txtValor.setText(String.format("%.2f", valorPadrao));
//     } else {
//       txtValor.setText("");
//     }
//   }

//   private void cadastrarMulta() {
//     try {
//       // Validações
//       if (txtPlaca.getText().trim().isEmpty()) {
//         JOptionPane.showMessageDialog(this, "Placa é obrigatória!", "Erro", JOptionPane.ERROR_MESSAGE);
//         return;
//       }

//       if (txtLocal.getText().trim().isEmpty()) {
//         JOptionPane.showMessageDialog(this, "Local é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
//         return;
//       }

//       // Cria a multa
//       String placa = txtPlaca.getText().trim();
//       String tipo = (String) cmbTipoInfracao.getSelectedItem();
//       double valor = Double.parseDouble(txtValor.getText().replace(",", "."));
//       LocalDate data = LocalDate.parse(txtData.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//       String local = txtLocal.getText().trim();

//       Multa multa = new Multa(placa, tipo, valor, data, local);

//       // Adiciona ao sistema Singleton
//       sistema.adicionarMulta(multa);

//       // Atualiza a tabela e estatísticas
//       atualizarTabela(sistema.listarTodas());
//       atualizarEstatisticas();

//       // Limpa o formulário
//       limparFormulario();

//       JOptionPane.showMessageDialog(this, "Multa cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

//     } catch (Exception ex) {
//       JOptionPane.showMessageDialog(this, "Erro ao cadastrar multa: " + ex.getMessage(), "Erro",
//           JOptionPane.ERROR_MESSAGE);
//     }
//   }

//   private void filtrarMultas() {
//     List<Multa> multasFiltradas;

//     String filtroPlaca = txtFiltroPlaca.getText().trim();
//     String filtroTipo = (String) cmbFiltroTipo.getSelectedItem();

//     if (!filtroPlaca.isEmpty()) {
//       multasFiltradas = sistema.filtrarPorPlaca(filtroPlaca);
//     } else if (!"Todas".equals(filtroTipo)) {
//       multasFiltradas = sistema.filtrarPorTipo(filtroTipo);
//     } else {
//       multasFiltradas = sistema.listarTodas();
//     }

//     atualizarTabela(multasFiltradas);
//   }

//   private void limparFiltros() {
//     txtFiltroPlaca.setText("");
//     cmbFiltroTipo.setSelectedIndex(0);
//     atualizarTabela(sistema.listarTodas());
//   }

//   private void atualizarTabela(List<Multa> multas) {
//     tableModel.setRowCount(0);
//     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

//     for (Multa m : multas) {
//       Object[] row = {
//           m.getPlaca(),
//           m.getTipoInfracao(),
//           String.format("R$ %.2f", m.getValor()),
//           m.getData().format(formatter),
//           m.getLocal()
//       };
//       tableModel.addRow(row);
//     }
//   }

//   private void atualizarEstatisticas() {
//     lblTotalMultas.setText("Total de Multas: " + sistema.getTotalMultas());
//     lblValorTotal.setText(String.format("Valor Total: R$ %.2f", sistema.getValorTotal()));
//   }

//   private void limparFormulario() {
//     txtPlaca.setText("");
//     cmbTipoInfracao.setSelectedIndex(0);
//     txtValor.setText("195.23");
//     txtData.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//     txtLocal.setText("");
//     txtPlaca.requestFocus();
//   }
// }
