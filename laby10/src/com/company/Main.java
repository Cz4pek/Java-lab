package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class Main {

    private JButton open = new JButton("Open");
    private JButton save = new JButton("Save");
    private JFrame frame = new JFrame("notatnik spaniały");
    private JEditorPane textArea = new JEditorPane();
    private JPanel panel = (JPanel) frame.getContentPane();
    private SpringLayout layout = new SpringLayout();

    public Main() {

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(800,800));

        panel.setLayout(layout);
        panel.setBackground(new Color(184, 184, 184));
        int sidePad = 40;

        textArea.setPreferredSize(new Dimension(700,650));
        panel.add(textArea);
        layout.putConstraint(SpringLayout.WEST, textArea, sidePad, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, textArea, 20, SpringLayout.NORTH, panel);


        panel.add(open);
        panel.add(save);
        layout.putConstraint(SpringLayout.WEST, open, sidePad, SpringLayout.WEST, frame);
        layout.putConstraint(SpringLayout.NORTH, open, 20, SpringLayout.SOUTH, textArea);
        layout.putConstraint(SpringLayout.WEST, save, 680, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, save, 20, SpringLayout.SOUTH, textArea);

        open.addActionListener((ActionEvent e) ->{

            JFileChooser fc = new JFileChooser();
            if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                File file = fc.getSelectedFile();
                String path = file.getAbsolutePath();
                String line, text;
                text = "";
                try {
                    BufferedReader br = new BufferedReader(new FileReader(path));
                    while ((line = br.readLine()) != null){
                        text = text.concat(line);
                        text= text.concat("\n");
                    }
                    br.close();
                    textArea.setText(text);
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }

        });

        save.addActionListener((ActionEvent e) ->{

            JFileChooser fc = new JFileChooser();
            if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
                File file = fc.getSelectedFile();
                String path = file.getAbsolutePath();
                try {
                    BufferedWriter wr = new BufferedWriter(new FileWriter(path));
                    wr.write(textArea.getText());
                    wr.close();
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }

        });

        frame.pack();
    }

    public static void main(String[] args) {

        Main main = new Main();
    }
}
