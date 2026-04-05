package UI;

import Factory.Tipo;
import Model.Ingresso;
import Repository.VendasRepository;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class Janela extends javax.swing.JFrame {

    private VendasRepository repository = new VendasRepository("vendas.csv");
    private JTable jTableVendas;

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
        setTitle("Sistema de Venda de Ingressos");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel superior
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnNovaVenda = new JButton("Nova Venda");
        JButton btnAtualizar = new JButton("Atualizar Lista");

        topPanel.add(btnNovaVenda);
        topPanel.add(btnAtualizar);

        jTableVendas = new JTable();
        jTableVendas.setRowHeight(25);
        jTableVendas.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] { "Evento", "Tipo", "Valor Final (R$)" }));
        JScrollPane scroll = new JScrollPane(jTableVendas);

        // Layout principal
        add(topPanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // Ações dos botões
        btnNovaVenda.addActionListener(e -> abrirModalCadastro());
        btnAtualizar.addActionListener(e -> inicializar());
    }

    private void abrirModalCadastro() {
        // Cria e exibe a janela modal
        ModalVenda modal = new ModalVenda(this, repository, () -> inicializar());
        modal.setVisible(true);
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

    // public static void main(String[] args) {
    // try {
    // // Tenta colocar o visual nativo do seu sistema operacional
    // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // SwingUtilities.invokeLater(() -> new Janela().setVisible(true));
    // }

    // private JTable jTableVendas;
}