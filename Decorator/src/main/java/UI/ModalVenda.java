package UI;

import Factory.IngressoFactory;
import Factory.Tipo;
import Model.Ingresso;
import Repository.VendasRepository;
import main.java.Model.Decorator.MeetAndGreet;

import javax.swing.*;
import java.awt.*;

public class ModalVenda extends JDialog {
    private JTextField txtEvento = new JTextField(20);
    private JTextField txtPreco = new JTextField(10);
    private JComboBox<Tipo> cbTipo = new JComboBox<>(Tipo.values());
    private JButton btnSalvar = new JButton("Confirmar");

    private VendasRepository repository;
    private Runnable callback;

    private JCheckBox chkMeet = new JCheckBox("Meet and Greet (+150,00)");
    private JCheckBox chkKit = new JCheckBox("Kit Merchandising (+50,00)");
    private JCheckBox chkTour = new JCheckBox("Backstage Tour (+100,00)");

    public ModalVenda(JFrame pai, VendasRepository repo, Runnable atualizarTabela) {
        super(pai, "Cadastrar Venda", true); // O 'true' ativa o comportamento Modal
        this.repository = repo;
        this.callback = atualizarTabela;

        // Layout do formulário
        JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        painel.add(new JLabel("Nome do Evento:"));
        painel.add(txtEvento);
        painel.add(new JLabel("Preço Base:"));
        painel.add(txtPreco);
        painel.add(new JLabel("Categoria:"));
        painel.add(cbTipo);
        painel.add(chkMeet);
        painel.add(chkKit);
        painel.add(chkTour);
        painel.add(new JLabel(""));
        painel.add(btnSalvar);

        add(painel);
        pack();
        setLocationRelativeTo(pai);

        btnSalvar.addActionListener(e -> salvar());
    }

    private void salvar() {
        try {
            Tipo tipo = (Tipo) cbTipo.getSelectedItem();
            // Factory Method sendo usado para criar o objeto correto
            Ingresso ingresso = IngressoFactory.getIngresso(tipo, txtEvento.getText(),
                    Double.parseDouble(txtPreco.getText()));

            // Decorator sendo aplicado conforme as opções selecionadas
            if (chkMeet.isSelected()) {
                ingresso = new MeetAndGreet(ingresso);
            }
            if (chkKit.isSelected()) {
                ingresso = new KitMerchandising(ingresso);
            }
            if (chkTour.isSelected()) {
                ingresso = new BackstageTour(ingresso);
            }

            // Persistência
            repository.salvarVenda(ingresso, tipo);

            // Template Method: Recibo formatado na classe pai
            JOptionPane.showMessageDialog(this, "Venda concluída!\n" + ingresso.gerarRecibo());

            callback.run(); // Atualiza a Janela principal
            dispose(); // Fecha o modal
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}