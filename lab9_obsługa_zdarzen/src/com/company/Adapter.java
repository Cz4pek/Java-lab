package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Adapter extends MouseAdapter {
    private Color color;
    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        Object o = e.getSource();
        JButton button = null;
        if (o instanceof JButton) button = (JButton)o;
        button.setBackground(color);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        Object o = e.getSource();
        JButton button = null;
        if (o instanceof JButton) button = (JButton)o;
        color = button.getBackground();
        button.setBackground(Color.RED);
    }
}
