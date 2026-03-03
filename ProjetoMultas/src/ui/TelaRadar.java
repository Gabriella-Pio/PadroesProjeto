package ui;

import models.Multa;
import services.SistemaMultas;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Tela de Radar - Simula um radar de rua que cadastra multas.
 * Múltiplas instâncias desta tela podem ser abertas simultaneamente,
 * mas TODAS acessam o MESMO Singleton SistemaMultas.
 */
public class TelaRadar extends JFrame {
  private SistemaMultas sistema;
  private JTextField txtPlaca;
  private JComboBox<String> cmbTipoInfracao;
  private JTextField txtValor;
  private JTextField txtLocal;
  private JLabel lblHashCode;
  private Map<String, Double> valoresPadrao;
  private static int contadorRadares = 0;
  private int numeroRadar;

  public TelaRadar() {
    numeroRadar = ++contadorRadares;

    // Obtém a instância ÚNICA do Singleton
    sistema = SistemaMultas.getInstance();

    inicializarValoresPadrao();
    configurarJanela();
    criarInterface();

    System.out.println("Radar " + numeroRadar + " iniciado - Instância Singleton: " + sistema.hashCode());
  }

  private void inicializarValoresPadrao() {
    valoresPadrao = new HashMap<>();
    valoresPadrao.put("Excesso de Velocidade", 195.23);
    valoresPadrao.put("Estacionamento Irregular", 130.16);
    valoresPadrao.put("Avanço de Sinal Vermelho", 293.47);
    valoresPadrao.put("Uso de Celular ao Volante", 293.47);
    valoresPadrao.put("Falta de Cinto de Segurança", 195.23);
    valoresPadrao.put("Ultrapassagem Indevida", 195.23);
    valoresPadrao.put("Outra", 0.0);
  }

  private void configurarJanela() {
    setTitle("Radar " + numeroRadar + " - Cadastro de Multas");
    setSize(450, 450);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Posiciona as janelas em locais diferentes
    int offset = (numeroRadar - 1) * 50;
    setLocation(50 + offset, 50 + offset);
  }

  private void criarInterface() {
    setLayout(new BorderLayout(10, 10));

    // Painel de título
    JPanel panelTitulo = new JPanel();
    panelTitulo.setBackground(new Color(231, 76, 60)); // Vermelho para radar
    panelTitulo.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

    JLabel lblTitulo = new JLabel("RADAR " + numeroRadar);
    lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
    lblTitulo.setForeground(Color.WHITE);
    panelTitulo.add(lblTitulo);

    add(panelTitulo, BorderLayout.NORTH);

    // Painel de formulário
    JPanel panelForm = new JPanel(new GridBagLayout());
    panelForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(8, 8, 8, 8);

    // Placa
    gbc.gridx = 0;
    gbc.gridy = 0;
    panelForm.add(new JLabel("Placa:"), gbc);
    gbc.gridx = 1;
    txtPlaca = new JTextField(15);
    txtPlaca.setFont(new Font("Monospaced", Font.BOLD, 14));
    panelForm.add(txtPlaca, gbc);

    // Tipo de Infração
    gbc.gridx = 0;
    gbc.gridy = 1;
    panelForm.add(new JLabel("Infração:"), gbc);
    gbc.gridx = 1;
    String[] tipos = {
        "Excesso de Velocidade",
        "Estacionamento Irregular",
        "Avanço de Sinal Vermelho",
        "Dirigir sem CNH",
        "Uso de Celular ao Volante",
        "Falta de Cinto de Segurança",
        "Ultrapassagem Indevida",
        "Outras Infrações"
    };
    cmbTipoInfracao = new JComboBox<>(tipos);
    cmbTipoInfracao.addActionListener(e -> atualizarValorPadrao());
    panelForm.add(cmbTipoInfracao, gbc);

    // Valor
    gbc.gridx = 0;
    gbc.gridy = 2;
    panelForm.add(new JLabel("Valor (R$):"), gbc);
    gbc.gridx = 1;
    txtValor = new JTextField("195.23");
    txtValor.setFont(new Font("Arial", Font.BOLD, 14));
    txtValor.setForeground(new Color(192, 57, 43));
    panelForm.add(txtValor, gbc);

    // Local
    gbc.gridx = 0;
    gbc.gridy = 3;
    panelForm.add(new JLabel("Local:"), gbc);
    gbc.gridx = 1;
    txtLocal = new JTextField(15);
    panelForm.add(txtLocal, gbc);

    // Botão Multar
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 2;
    JButton btnMultar = new JButton("REGISTRAR MULTA");
    btnMultar.setBackground(new Color(231, 76, 60));
    btnMultar.setForeground(Color.WHITE);
    btnMultar.setFont(new Font("Arial", Font.BOLD, 16));
    btnMultar.setFocusPainted(false);
    btnMultar.addActionListener(e -> registrarMulta());
    panelForm.add(btnMultar, gbc);

    add(panelForm, BorderLayout.CENTER);

    // Painel de informação do Singleton
    JPanel panelInfo = new JPanel(new BorderLayout());
    panelInfo.setBackground(new Color(241, 196, 15));
    panelInfo.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    JLabel lblSingletonLabel = new JLabel("Instância Singleton:");
    lblSingletonLabel.setFont(new Font("Arial", Font.BOLD, 12));

    lblHashCode = new JLabel(String.valueOf(sistema.hashCode()));
    lblHashCode.setFont(new Font("Monospaced", Font.BOLD, 14));
    lblHashCode.setForeground(new Color(41, 128, 185));

    panelInfo.add(lblSingletonLabel, BorderLayout.WEST);
    panelInfo.add(lblHashCode, BorderLayout.CENTER);

    add(panelInfo, BorderLayout.SOUTH);
  }

  private void atualizarValorPadrao() {
    String tipoSelecionado = (String) cmbTipoInfracao.getSelectedItem();
    Double valorPadrao = valoresPadrao.get(tipoSelecionado);
    if (valorPadrao != null && valorPadrao > 0) {
      txtValor.setText(String.format("%.2f", valorPadrao));
    } else {
      txtValor.setText("");
    }
  }

  private void registrarMulta() {
    try {
      // Validações
      if (txtPlaca.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "Placa é obrigatória!",
            "Erro - Radar " + numeroRadar,
            JOptionPane.ERROR_MESSAGE);
        return;
      }

      if (txtLocal.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "Local é obrigatório!",
            "Erro - Radar " + numeroRadar,
            JOptionPane.ERROR_MESSAGE);
        return;
      }

      // Cria a multa
      String placa = txtPlaca.getText().trim();
      String tipo = (String) cmbTipoInfracao.getSelectedItem();
      double valor = Double.parseDouble(txtValor.getText().replace(",", "."));
      String local = txtLocal.getText().trim();
      LocalDate data = LocalDate.now();
      String nomeRadar = "Radar " + numeroRadar;
      
      Multa multa = new Multa(placa, tipo, valor, data, local, nomeRadar);

      // Adiciona ao Singleton
      sistema.adicionarMulta(multa);

      // Feedback visual
      JOptionPane.showMessageDialog(this,
          "Multa registrada no sistema central!\n" +
              "Placa: " + placa + "\n" +
              "Valor: R$ " + String.format("%.2f", valor) + "\n\n",
          "Radar " + numeroRadar,
          JOptionPane.INFORMATION_MESSAGE);

      // Limpa o formulário
      limparFormulario();

    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(this,
          "Valor inválido! Use apenas números.",
          "Erro - Radar " + numeroRadar,
          JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this,
          "Erro ao registrar multa: " + ex.getMessage(),
          "Erro - Radar " + numeroRadar,
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void limparFormulario() {
    txtPlaca.setText("");
    cmbTipoInfracao.setSelectedIndex(0);
    txtValor.setText("0.0");
    txtLocal.setText("");
    txtPlaca.requestFocus();
  }
}
