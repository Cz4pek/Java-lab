package app;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.plaf.DimensionUIResource;

public class App {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Okienko piękne");
        JButton button0 = new JButton("guziczek " + 0);
        JButton button1 = new JButton("guziczek " + 1);
        JButton button2 = new JButton("guziczek " + 2);
        JButton button3 = new JButton("guziczek " + 3);
        // frame.getContentPane().setLayout(new FlowLayout());
        // frame.getContentPane().setLayout(new GridLayout(2,4));
        // frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),
        // BoxLayout.Y_AXIS));
        SpringLayout layout = new SpringLayout();
        frame.getContentPane().setLayout(layout);
        layout.putConstraint(SpringLayout.WEST, button0, 5, SpringLayout.WEST, frame.getContentPane());
        layout.putConstraint(SpringLayout.WEST, button1, 5, SpringLayout.NORTH, button0);
        layout.putConstraint(SpringLayout.WEST, button2, 5, SpringLayout.EAST, button1);
        layout.putConstraint(SpringLayout.WEST, button3, 5, SpringLayout.EAST, button2);
        frame.getContentPane().add(button0);
        frame.getContentPane().add(button1);
        frame.getContentPane().add(button2);
        frame.getContentPane().add(button3);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}