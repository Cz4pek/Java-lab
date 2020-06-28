package com.company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Frame extends JFrame implements ActionListener, ItemListener, ChangeListener {

    private JTextField nameTextField = new JTextField(15);
    private JTextField surnameTextField = new JTextField(15);
    private JRadioButton genderFemale = new JRadioButton("Kobieta");
    private JRadioButton genderMale = new JRadioButton("Mężczyzna");
    private JSpinner ageSpiner = new JSpinner();
    private JList<String> animalList = new JList<>(new String[]{"Pies", "Kot", "Chomik", "Królik", "Pająk", "Rybki", "Owady"});
    private JButton confirm = new JButton("Zatwierdź");
    private JButton cancel = new JButton("Anuluj");
    private JScrollPane animalPane = new JScrollPane(animalList);

    public Frame(String title) {
        super(title);
        if (JOptionPane.showConfirmDialog(this, "Czy chcesz wziąć udział w ankiecie?", "Ankieta", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION)
            System.exit(0);
        this.setSize(new Dimension(300, 300));
        this.setLocation(850, 390);
        this.setResizable(false);
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel nameLabel = new JLabel("Imię:");
        JLabel surnameLabel = new JLabel("Nazwisko:");
        JLabel genderLabel = new JLabel("Płeć:");
        JLabel ageLabel = new JLabel("Wiek:");
        JLabel animalLabel = new JLabel("Wybierz zwierzę, które posiadasz w domu:");
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(genderFemale);
        radioGroup.add(genderMale);
        JFormattedTextField ftf = getTextField(ageSpiner);
        ftf.setColumns(2);
        animalList.setLayoutOrientation(JList.VERTICAL_WRAP);
        animalList.setVisibleRowCount(5);
        animalPane.setPreferredSize(new Dimension(120, 80));

        genderFemale.setEnabled(false);
        genderMale.setEnabled(false);
        ageSpiner.setEnabled(false);
        animalList.setEnabled(false);

        this.add(nameLabel);
        layout.putConstraint(SpringLayout.WEST, nameLabel, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 15, SpringLayout.NORTH, this);
        this.add(nameTextField);
        layout.putConstraint(SpringLayout.WEST, nameTextField, 41, SpringLayout.EAST, nameLabel);
        layout.putConstraint(SpringLayout.NORTH, nameTextField, 15, SpringLayout.NORTH, this);

        this.add(surnameLabel);
        layout.putConstraint(SpringLayout.WEST, surnameLabel, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, surnameLabel, 10, SpringLayout.SOUTH, nameLabel);
        this.add(surnameTextField);
        layout.putConstraint(SpringLayout.WEST, surnameTextField, 10, SpringLayout.EAST, surnameLabel);
        layout.putConstraint(SpringLayout.NORTH, surnameTextField, 10, SpringLayout.SOUTH, nameLabel);

        this.add(genderLabel);
        layout.putConstraint(SpringLayout.WEST, genderLabel, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, genderLabel, 10, SpringLayout.SOUTH, surnameLabel);
        this.add(genderFemale);
        layout.putConstraint(SpringLayout.WEST, genderFemale, 10, SpringLayout.EAST, genderLabel);
        layout.putConstraint(SpringLayout.NORTH, genderFemale, 7, SpringLayout.SOUTH, surnameLabel);
        this.add(genderMale);
        layout.putConstraint(SpringLayout.WEST, genderMale, 10, SpringLayout.EAST, genderFemale);
        layout.putConstraint(SpringLayout.NORTH, genderMale, 7, SpringLayout.SOUTH, surnameLabel);

        this.add(ageLabel);
        layout.putConstraint(SpringLayout.WEST, ageLabel, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, ageLabel, 10, SpringLayout.SOUTH, genderLabel);
        this.add(ageSpiner);
        layout.putConstraint(SpringLayout.WEST, ageSpiner, 10, SpringLayout.EAST, ageLabel);
        layout.putConstraint(SpringLayout.NORTH, ageSpiner, 9, SpringLayout.SOUTH, genderLabel);

        this.add(animalLabel);
        layout.putConstraint(SpringLayout.WEST, animalLabel, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, animalLabel, 10, SpringLayout.SOUTH, ageLabel);
        this.add(animalPane);
        layout.putConstraint(SpringLayout.WEST, animalPane, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, animalPane, 10, SpringLayout.SOUTH, animalLabel);

        this.add(confirm);
        layout.putConstraint(SpringLayout.WEST, confirm, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, confirm, 230, SpringLayout.NORTH, this);
        this.add(cancel);
        layout.putConstraint(SpringLayout.WEST, cancel, 10, SpringLayout.EAST, confirm);
        layout.putConstraint(SpringLayout.NORTH, cancel, 230, SpringLayout.NORTH, this);

        surnameTextField.addActionListener(this);
        genderFemale.addItemListener(this);
        genderMale.addItemListener(this);
        ageSpiner.addChangeListener(this);
        confirm.addActionListener((ActionEvent e) -> {
            StringBuilder message = new StringBuilder("Wprowadzono następujące dane:\n");
            message.append("Imię: ").append(nameTextField.getText()).append("\n");
            message.append("Nazwisko: ").append(surnameTextField.getText()).append("\n");
            message.append("Płeć: ").append((genderFemale.isSelected()) ? genderFemale.getText() : genderMale.getText()).append("\n");
            message.append("Wiek: ").append(ftf.getText()).append("\n");
            String animals = animalList.getSelectedValuesList().toString();
            animals = animals.replaceAll("[\\[\\]]", "");
            message.append("Zwierzęta: ").append(animals).append("\n");
            JOptionPane.showMessageDialog(this, message.toString(), "Dane",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        cancel.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(this, "Operacja została anulowana", "Anulowano",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        });

        Adapter adapter = new Adapter();
        confirm.addMouseListener(adapter);
        cancel.addMouseListener(adapter);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Frame frame = new Frame("okienko");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Pattern p = Pattern.compile("\\s+");
        Matcher m = p.matcher(nameTextField.getText());
        boolean b1 = m.matches();
        m = p.matcher(surnameTextField.getText());
        boolean b2 = m.matches();
        if (e.getSource() == surnameTextField) {
            if (!nameTextField.getText().equals("") && !surnameTextField.getText().equals("") && !b1 && !b2) {
                genderFemale.setEnabled(true);
                genderMale.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "Proszę wprowadzić imię i nazwisko", "Brak danych",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        ageSpiner.setEnabled(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        animalList.setEnabled(true);
    }

    public JFormattedTextField getTextField(JSpinner spinner) {
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            return ((JSpinner.DefaultEditor) editor).getTextField();
        } else {
            System.err.println("Unexpected editor type: "
                    + spinner.getEditor().getClass()
                    + " isn't a descendant of DefaultEditor");
            return null;
        }
    }
}
