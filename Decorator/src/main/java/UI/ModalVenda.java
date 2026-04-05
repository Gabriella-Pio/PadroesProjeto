package UI;

import Factory.IngressoFactory;
import Factory.Tipo;
import Model.Ingresso;
import Repository.VendasRepository;
import main.java.Model.Decorator.MeetAndGreet;
import main.java.Model.Decorator.KitMerchandising;
import main.java.Model.Decorator.BackstageTour;

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
        super(pai, "Cadastrar Venda", true);
        this.repository = repo;
        this.callback = atualizarTabela;

        setLayout(new BorderLayout());

        // Painel de Informações Básicas
        JPanel painelInfo = new JPanel(new GridLayout(3, 2, 10, 10));
        painelInfo.setBorder(BorderFactory.createTitledBorder("Informações Básicas"));

        painelInfo.add(new JLabel("Nome do Evento:"));
        painelInfo.add(txtEvento);
        painelInfo.add(new JLabel("Preço Base:"));
        painelInfo.add(txtPreco);
        painelInfo.add(new JLabel("Categoria:"));
        painelInfo.add(cbTipo);

        // Painel de Experiências Adicionais (Checkboxes)
        JPanel painelAdicionais = new JPanel(new GridLayout(3, 1));
        painelAdicionais.setBorder(BorderFactory.createTitledBorder("Experiências Adicionais (Decorator)"));
        painelAdicionais.add(chkMeet);
        painelAdicionais.add(chkKit);
        painelAdicionais.add(chkTour);

        // Painel do Botão
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotao.add(btnSalvar);

        // Container Central para empilhar os painéis
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        container.add(painelInfo);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(painelAdicionais);

        add(container, BorderLayout.CENTER);
        add(painelBotao, BorderLayout.SOUTH);

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