package app;

import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.SwingConstants;

/**
 * @author Witold Gombrowicz
 */
public class Okienko extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Okienko(Color color, String title) throws HeadlessException {
        super(title);
        this.setBackground(color);
        this.setPreferredSize(new Dimension(800, 300));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        JButton[] buttons = new JButton[5];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("Opcja " + (i + 1));
            buttonPanel.add(buttons[i]);
        }
        this.add(buttonPanel, BorderLayout.WEST);
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Opcje"));
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2));
        this.add(formPanel, BorderLayout.EAST);
        for (int i = 0; i < 10; i++) {
            formPanel.add((i % 2 == 0) ? new JLabel("Pozycja " + i + ": ", SwingConstants.RIGHT) : new JTextField(20));
        }
        formPanel.setBackground(Color.lightGray);
        formPanel.setBorder(BorderFactory.createTitledBorder("Formularz"));
        JPanel bottomPanel = new JPanel();
        this.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(Color.blue);
        bottomPanel.add(new JButton("OK"));
        JScrollPane tablePane = new JScrollPane();
        this.add(tablePane, BorderLayout.CENTER);
        Object[][] tableData = { { 1, "Adam", "Mickiewicz" }, { 2, "Juliusz", "Słowacki" }, { 3, "Cyprian", "Norwid" },
                { 4, "Bolesław", "Leśmian" }, { 5, "Witold", "Gombrowicz" } };
        Object[] tableNames = { "Lp.", "Imię", "Nazwisko" };
        JTable table = new JTable(tableData, tableNames);
        tablePane.setViewportView(table);
        JMenuBar topMenu = new JMenuBar();
        this.setJMenuBar(topMenu);
        JMenu m1 = new JMenu("Plik");
        JMenu m2 = new JMenu("Edycja");
        topMenu.add(m1);
        topMenu.add(m2);
        m1.add(new JMenuItem("Zapisz"));
        m1.add(new JMenuItem("Otwórz"));
        m2.add(new JMenuItem("Cofnij"));
        this.pack();

    }

    public static void main(String[] args) throws Exception {
        Okienko okno = new Okienko(Color.WHITE, "Okienko elegancki");

    }
}