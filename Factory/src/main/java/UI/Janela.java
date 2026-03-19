package UI;

import Factory.IngressoFactory;
import Factory.Tipo;
import Model.Ingresso;
import Repository.VendasRepository;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class Janela extends javax.swing.JFrame {

    private VendasRepository repository = new VendasRepository("src/main/java/Data/vendas.csv");

    private JTextField txtEvento = new JTextField();
    private JTextField txtPreco = new JTextField();
    private JComboBox<Tipo> cbTipo = new JComboBox<>(Tipo.values());
    private JButton btnVender = new JButton("Finalizar Venda");

    public Janela() {
        initComponents();
        inicializar();
    }

    private void inicializar() {
        try {
            Iterator<Ingresso> it = repository.obterVendas();
            imprimirVendasNaGrid(it);
        } catch (Exception e) {
            System.out.println("Aviso: " + e.getMessage());
        }
    }

    private void initComponents() {
        setTitle("Sistema de Gestão de Ingressos");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelCadastro = new JPanel(new GridLayout(2, 4, 10, 10));
        painelCadastro.setBorder(BorderFactory.createTitledBorder("Nova Venda"));

        JButton btnAtualizar = new JButton("Atualizar Lista");

        painelCadastro.add(new JLabel("Evento:"));
        painelCadastro.add(txtEvento);
        painelCadastro.add(new JLabel("Preço Base:"));
        painelCadastro.add(txtPreco);
        painelCadastro.add(new JLabel("Tipo:"));
        painelCadastro.add(cbTipo);
        painelCadastro.add(new JLabel(""));
        painelCadastro.add(btnVender);
        painelCadastro.add(new JLabel(""));
        painelCadastro.add(btnAtualizar);

        // Configuração da Tabela
        jTableVendas = new JTable();
        jTableVendas.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] { "Evento", "Tipo", "Valor Final (R$)" }));
        JScrollPane scroll = new JScrollPane(jTableVendas);

        // Layout Principal
        add(painelCadastro, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // Ação do Botão Vender e do de Atualizar
        btnVender.addActionListener(e -> acaoVender());
        btnAtualizar.addActionListener(e -> inicializar());
    }

    private void acaoVender() {
        try {
            String evento = txtEvento.getText();
            double preco = Double.parseDouble(txtPreco.getText());
            Tipo tipo = (Tipo) cbTipo.getSelectedItem();

            // Uso da Factory para criar o objeto correto
            Ingresso novo = IngressoFactory.getIngresso(tipo, evento, preco);

            // Repository persiste no CSV
            repository.salvarVenda(novo, tipo);

            JOptionPane.showMessageDialog(this, "Venda realizada!\n" + novo.gerarRecibo());
            inicializar();

            // Limpar campos
            txtEvento.setText("");
            txtPreco.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }

    private void imprimirVendasNaGrid(Iterator<Ingresso> conjunto) {
        DefaultTableModel model = (DefaultTableModel) jTableVendas.getModel();
        model.setNumRows(0);

        while (conjunto.hasNext()) {
            Ingresso ing = conjunto.next();
            model.addRow(new Object[] {
                    ing.getEvento(),
                    ing.getDescricaoTipo(),
                    String.format("%.2f", ing.calcularValorFinal())
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Janela().setVisible(true));
    }

    private JTable jTableVendas;
}