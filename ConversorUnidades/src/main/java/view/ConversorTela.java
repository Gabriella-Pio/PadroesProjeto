package view;

import javax.swing.*;
import target.IMedidaMetrica;
import target.ReguaMetrica;
import adapter.UnidadeAdapter;
import adaptee.ReguaAmericana;

public class ConversorTela extends JFrame {

    private IMedidaMetrica medida = null;

    private JTextField jTextFieldValor;
    private JTextArea jTextAreaResultado;
    private JButton jButtonMetrico;
    private JButton jButtonImperial;

    public ConversorTela() {
        initComponents();
        setLocationRelativeTo(null);
        medida = new ReguaMetrica(0); // inicia com a classe nativa
    }

    private void initComponents() {
        setTitle("CONVERSOR DE UNIDADES");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("CONVERSOR DE UNIDADES");
        titulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
        titulo.setAlignmentX(CENTER_ALIGNMENT);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Valor: "));
        jTextFieldValor = new JTextField(10);
        inputPanel.add(jTextFieldValor);

        JPanel btnPanel = new JPanel();
        jButtonMetrico = new JButton("MÉTRICO (metros)");
        jButtonImperial = new JButton("IMPERIAL (polegadas -> metros)");
        jButtonMetrico.addActionListener(e -> calcularMetrico());
        jButtonImperial.addActionListener(e -> calcularImperial());
        btnPanel.add(jButtonMetrico);
        btnPanel.add(jButtonImperial);

        jTextAreaResultado = new JTextArea(5, 35);
        jTextAreaResultado.setEditable(false);
        jTextAreaResultado.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 13));
        jTextAreaResultado.setBorder(BorderFactory.createTitledBorder("RESULTADO"));

        panel.add(titulo);
        panel.add(Box.createVerticalStrut(15));
        panel.add(inputPanel);
        panel.add(Box.createVerticalStrut(8));
        panel.add(btnPanel);
        panel.add(Box.createVerticalStrut(8));
        panel.add(new JScrollPane(jTextAreaResultado));

        add(panel);
        pack();
    }

    // Usa ReguaMetrica diretamente — sem Adapter
    private void calcularMetrico() {
        try {
            double valor = Double.parseDouble(jTextFieldValor.getText());
            medida = new ReguaMetrica(valor);
            double resultado = medida.getDistanciaEmMetros();
            jTextAreaResultado.setText(
                    "Entrada: " + valor + " metros\n" +
                            "Resultado: " + resultado + " metros\n\n");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite um número válido.");
        }
    }

    // Usa ReguaAmericana via UnidadeAdapter — COM Adapter
    private void calcularImperial() {
        try {
            double valor = Double.parseDouble(jTextFieldValor.getText());
            ReguaAmericana reguaEUA = new ReguaAmericana(valor);
            medida = new UnidadeAdapter(reguaEUA); // Adapter em ação!
            double resultado = medida.getDistanciaEmMetros();
            jTextAreaResultado.setText(
                    "Entrada: " + valor + " polegadas\n" +
                            "Resultado: " + String.format("%.4f", resultado) + " metros\n\n");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite um número válido.");
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> new ConversorTela().setVisible(true));
    }
}