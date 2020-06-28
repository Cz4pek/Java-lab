package com.company;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{


    public static void main(String[] args) {
        JFrame frame = new JFrame("okienko");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().add(new panel());
        frame.setPreferredSize(new Dimension(800,600));
        frame.pack();
        frame.setVisible(true);
    }
}
