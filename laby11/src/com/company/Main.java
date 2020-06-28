package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Main extends JFrame {

    JPanel panel = (JPanel)getContentPane();

    public Main(String title) throws HeadlessException {
        super(title);
        setPreferredSize(new Dimension(1080,1000));
        SpringLayout layout = new SpringLayout();
        panel.setBackground(Color.PINK);
        panel.setLayout(layout);

        JEditorPane oknoPrzegladarki = new JEditorPane();
        oknoPrzegladarki.setPreferredSize(new Dimension(1000,775));
        //oknoPrzegladarki.setEnabled(false);
        panel.add(oknoPrzegladarki);
        layout.putConstraint(SpringLayout.WEST, oknoPrzegladarki, 32, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, oknoPrzegladarki, 10, SpringLayout.NORTH, panel);

        JTextArea textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(1000, 100));
        panel.add(textArea);
        layout.putConstraint(SpringLayout.WEST, textArea, 32, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, textArea, 10, SpringLayout.SOUTH, oknoPrzegladarki);

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(800, 25));
        panel.add(textField);
        layout.putConstraint(SpringLayout.WEST, textField, 42, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, textField, 10, SpringLayout.SOUTH, textArea);

        JButton button = new JButton("Idź do");
        button.setPreferredSize(new Dimension(120,25));
        panel.add(button);
        layout.putConstraint(SpringLayout.WEST, button, 60, SpringLayout.EAST, textField);
        layout.putConstraint(SpringLayout.NORTH, button, 10, SpringLayout.SOUTH, textArea);

        button.addActionListener((ActionEvent e )->{
            String text = textField.getText();
            URL adres = null;
            try {
                adres = new URL(text);
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            try {
                oknoPrzegladarki.setPage(adres);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                BufferedReader getHTML = new BufferedReader(new InputStreamReader(adres.openStream()));
                String line;
                while ((line = getHTML.readLine()) != null){
                    textArea.append(line);
                }
                getHTML.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    public static void main(String[] args) {
        Main okno = new Main("Internet explorer");
    }
}
