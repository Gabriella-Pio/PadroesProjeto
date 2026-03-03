package view;

import adapter.IPlayableItem;
import controller.MusicController;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

/**
 *
 * @author gabriella
 */
public class TelaPrincipal extends JFrame {

    private static final Color BG_DARK = new Color(0x0F0F14);
    private static final Color BG_CARD = new Color(0x1A1A24);
    private static final Color BG_ITEM = new Color(0x22222E);
    private static final Color BG_ITEM_SEL = new Color(0x2E2E42);
    private static final Color ACCENT = new Color(0x7C6AF7);
    private static final Color ACCENT_HOVER = new Color(0x9B8DFF);
    private static final Color TEXT_PRIMARY = new Color(0xF0EFF8);
    private static final Color TEXT_SECONDARY = new Color(0x9A97B0);
    private static final Color SEPARATOR = new Color(0x2A2A3A);

    private static final Font FONT_TITLE = new Font("SansSerif", Font.BOLD, 22);
    private static final Font FONT_LABEL = new Font("SansSerif", Font.PLAIN, 13);
    private static final Font FONT_ITEM = new Font("Monospaced", Font.PLAIN, 14);
    private static final Font FONT_SMALL = new Font("SansSerif", Font.PLAIN, 11);

    private DefaultListModel<IPlayableItem> listModel;
    private JList<IPlayableItem> musicList;
    private JButton btnPlay;
    // private JButton btnRefresh;
    private JLabel lblNowPlaying;
    private JLabel lblStatus;

    private final MusicController controller = new MusicController();

    public TelaPrincipal() {
        super("Musicoteca");
        initUI();
        loadMusics();
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(740, 620);
        setMinimumSize(new Dimension(560, 460));
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG_DARK);
        getContentPane().setLayout(new BorderLayout(0, 0));

        add(buildHeader(), BorderLayout.NORTH);
        add(buildCenter(), BorderLayout.CENTER);
        add(buildFooter(), BorderLayout.SOUTH);
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout(16, 0));
        header.setBackground(BG_CARD);
        header.setBorder(new CompoundBorder(
                new MatteBorder(0, 0, 1, 0, SEPARATOR),
                new EmptyBorder(18, 24, 18, 24)));

        JLabel title = new JLabel("Musicoteca");
        title.setFont(FONT_TITLE);
        title.setForeground(TEXT_PRIMARY);

        // btnRefresh = buildIconButton("Atualizar", ACCENT, ACCENT_HOVER);
        // btnRefresh.addActionListener(e -> loadMusics());

        header.add(title, BorderLayout.WEST);
        // header.add(btnRefresh, BorderLayout.EAST);
        return header;
    }

    private JPanel buildCenter() {
        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(BG_DARK);
        center.setBorder(new EmptyBorder(16, 20, 0, 20));

        listModel = new DefaultListModel<>();
        musicList = new JList<>(listModel);
        musicList.setCellRenderer(new MusicCellRenderer());
        musicList.setBackground(BG_DARK);
        musicList.setForeground(TEXT_PRIMARY);
        musicList.setSelectionBackground(BG_ITEM_SEL);
        musicList.setSelectionForeground(TEXT_PRIMARY);
        musicList.setFixedCellHeight(56);
        musicList.setBorder(null);
        musicList.setFont(FONT_ITEM);
        musicList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2)
                    playSelected();
            }
        });
        musicList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting())
                updateNowPlaying();
        });

        JScrollPane scroll = new JScrollPane(musicList);
        scroll.setBorder(new LineBorder(SEPARATOR, 1, true));
        scroll.setBackground(BG_DARK);
        scroll.getViewport().setBackground(BG_DARK);
        scroll.getVerticalScrollBar().setUI(new SlimScrollBarUI());

        center.add(scroll, BorderLayout.CENTER);
        return center;
    }

    private JPanel buildFooter() {
        JPanel footer = new JPanel(new BorderLayout(12, 0));
        footer.setBackground(BG_CARD);
        footer.setBorder(new CompoundBorder(
                new MatteBorder(1, 0, 0, 0, SEPARATOR),
                new EmptyBorder(14, 24, 14, 24)));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(BG_CARD);

        JLabel nowLabel = new JLabel("TOCANDO AGORA");
        nowLabel.setFont(FONT_SMALL);
        nowLabel.setForeground(ACCENT);

        lblNowPlaying = new JLabel("—");
        lblNowPlaying.setFont(FONT_LABEL);
        lblNowPlaying.setForeground(TEXT_PRIMARY);

        lblStatus = new JLabel("Nenhuma faixa selecionada");
        lblStatus.setFont(FONT_SMALL);
        lblStatus.setForeground(TEXT_SECONDARY);

        infoPanel.add(nowLabel);
        infoPanel.add(Box.createVerticalStrut(2));
        infoPanel.add(lblNowPlaying);
        infoPanel.add(Box.createVerticalStrut(1));
        infoPanel.add(lblStatus);

        btnPlay = buildIconButton("Reproduzir", ACCENT, ACCENT_HOVER);
        btnPlay.setPreferredSize(new Dimension(160, 42));
        btnPlay.addActionListener(e -> playSelected());

        footer.add(infoPanel, BorderLayout.WEST);
        footer.add(btnPlay, BorderLayout.EAST);
        return footer;
    }

    // Lógica
    private void loadMusics() {
        lblStatus.setText("Carregando...");
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                controller.loadMusics(listModel);
                return null;
            }

            @Override
            protected void done() {
                int n = listModel.getSize();
                lblStatus.setText(n == 0 ? "Nenhuma música encontrada" : n + " faixas encontradas");
            }
        };
        worker.execute();
    }

    private void playSelected() {
        IPlayableItem item = musicList.getSelectedValue();
        if (item == null) {
            lblStatus.setText("Selecione uma faixa primeiro");
            return;
        }
        controller.playMusic(item);
        lblNowPlaying.setText(item.getTrackName());
        lblStatus.setText(item.getArtistName());
    }

    private void updateNowPlaying() {
        IPlayableItem item = musicList.getSelectedValue();
        if (item != null) {
            lblNowPlaying.setText(item.getTrackName());
            lblStatus.setText(item.getArtistName());
        }
    }

    // Helpers de UI
    private JButton buildIconButton(String text, Color bg, Color hover) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isRollover() ? hover : bg);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 10, 10));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(8, 20, 8, 20));
        return btn;
    }

    // Cell Renderer
    private static class MusicCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(
                JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            JPanel row = new JPanel(new BorderLayout(12, 0));
            row.setBorder(new CompoundBorder(
                    new MatteBorder(0, 0, 1, 0, SEPARATOR),
                    new EmptyBorder(0, 12, 0, 12)));
            row.setOpaque(true);
            row.setBackground(isSelected ? BG_ITEM_SEL : (index % 2 == 0 ? BG_ITEM : BG_DARK));

            // Número da faixa
            JLabel num = new JLabel(String.format("%02d", index + 1));
            num.setFont(new Font("Monospaced", Font.PLAIN, 12));
            num.setForeground(TEXT_SECONDARY);
            num.setPreferredSize(new Dimension(32, 0));

            // Info principal
            JPanel info = new JPanel();
            info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
            info.setOpaque(false);

            if (value instanceof IPlayableItem item) {
                JLabel track = new JLabel(item.getTrackName());
                track.setFont(new Font("SansSerif", Font.PLAIN, 14));
                track.setForeground(isSelected ? Color.WHITE : TEXT_PRIMARY);

                JLabel artist = new JLabel(item.getArtistName());
                artist.setFont(new Font("SansSerif", Font.PLAIN, 11));
                artist.setForeground(isSelected ? new Color(0xCCC9F0) : TEXT_SECONDARY);

                info.add(track);
                info.add(artist);

                JLabel sourceLabel = new JLabel(item.getSource().equalsIgnoreCase("YOUTUBE") ? " YT " : " DISK ");
                sourceLabel.setFont(new Font("SansSerif", Font.BOLD, 9));
                sourceLabel.setOpaque(true);
                sourceLabel.setForeground(Color.WHITE);

                if (item.getSource().equalsIgnoreCase("YOUTUBE")) {
                    sourceLabel.setBackground(new Color(0xFF0000));
                } else {
                    sourceLabel.setBackground(new Color(0x4A4A6A));
                }

                info.add(Box.createVerticalStrut(2));
                info.add(sourceLabel);
            } else {
                JLabel fallback = new JLabel(value != null ? value.toString() : "");
                fallback.setForeground(TEXT_PRIMARY);
                info.add(fallback);
            }

            // Ícone de play ao passar (hint visual)
            JLabel playHint = new JLabel(isSelected ? ">" : "");
            playHint.setFont(new Font("SansSerif", Font.PLAIN, 12));
            playHint.setForeground(ACCENT);
            playHint.setPreferredSize(new Dimension(20, 0));

            row.add(num, BorderLayout.WEST);
            row.add(info, BorderLayout.CENTER);
            row.add(playHint, BorderLayout.EAST);

            return row;
        }
    }

    // Scrollbar fina e estilizada
    private static class SlimScrollBarUI extends javax.swing.plaf.basic.BasicScrollBarUI {
        @Override
        protected void configureScrollBarColors() {
            thumbColor = new Color(0x4A4A6A);
            trackColor = BG_DARK;
        }

        @Override
        protected JButton createDecreaseButton(int o) {
            return zeroButton();
        }

        @Override
        protected JButton createIncreaseButton(int o) {
            return zeroButton();
        }

        private JButton zeroButton() {
            JButton b = new JButton();
            b.setPreferredSize(new Dimension(0, 0));
            return b;
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(thumbColor);
            g2.fill(new RoundRectangle2D.Float(r.x + 2, r.y + 2, r.width - 4, r.height - 4, 6, 6));
            g2.dispose();
        }
    }

    // Main
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}