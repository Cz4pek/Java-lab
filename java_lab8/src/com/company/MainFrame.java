package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements ActionListener {
    private JPanel  content;
    public MainFrame() throws HeadlessException {
        super("Okienko lab8");
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int)(width/3.5), (int)height/4);
        content = (JPanel) this.getContentPane();
        content.setBackground(Color.PINK);
        this.setSize(new Dimension(300,300));
        SpringLayout layout = new SpringLayout();
        content.setLayout(layout);
        JTextField text = new JTextField(20);
        content.add(text);
        layout.putConstraint(SpringLayout.WEST, text, 30, SpringLayout.WEST, content);
        layout.putConstraint(SpringLayout.NORTH, text, 10, SpringLayout.NORTH, content);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel1 = new JPanel();
        BoxLayout box = new BoxLayout(panel1, BoxLayout.X_AXIS);
        panel1.setPreferredSize(new Dimension(280,30));
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        content.add(panel1);
        layout.putConstraint(SpringLayout.NORTH, panel1, 40, SpringLayout.SOUTH, text);
        layout.putConstraint(SpringLayout.WEST, panel1, 2, SpringLayout.WEST, content);
        String[] items = {"1","2","3"};
        JComboBox combo = new JComboBox(items);
        content.add(combo);
        layout.putConstraint(SpringLayout.NORTH, combo, 10, SpringLayout.SOUTH, panel1);
        JLabel label = new JLabel("Combobox elegancki");
        content.add(label);
        layout.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.EAST, combo);
        layout.putConstraint(SpringLayout.NORTH, label, 10, SpringLayout.SOUTH, panel1);
        label.setFont(new Font(null, Font.BOLD, 20));
        button1.addActionListener(this);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object event = e.getSource();
                JButton button = null;

                if(event instanceof JButton)
                    button = (JButton) event;

                if(button != null)
                    System.out.println("Wciśnięto " + button.getText());
            }
        });
        button3.addActionListener((ActionEvent e) ->{
            Object event = e.getSource();
            JButton button = null;

            if(event instanceof JButton)
                button = (JButton) event;

            if(button != null)
                System.out.println("Wciśnięto " + button.getText());
        });
        this.addWindowListener (new Adapter());
        Listener listener = new Listener(label);
        combo.addItemListener(listener);
        text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField text = (JTextField) e.getSource();
                label.setText(combo.getSelectedItem() + " " + text.getText());
            }
        });
        //this.pack();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();
        JButton button = null;

        if(event instanceof JButton)
            button = (JButton) event;

        if(button != null)
            System.out.println("Wciśnięto " + button.getText());
    }
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
    private class Adapter extends WindowAdapter{
        @Override
        public void windowOpened(WindowEvent e) {
            super.windowOpened(e);
            JOptionPane.showMessageDialog(e.getWindow(), "Witaj w okienku które nic nie robi", "Witam",
                    JOptionPane.PLAIN_MESSAGE);
        }

        @Override
        public void windowClosed(WindowEvent e) {
//            super.windowClosed(e);
            System.exit(0);
        }
    }

}
