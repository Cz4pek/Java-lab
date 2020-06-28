package com.company;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Listener implements ItemListener {

    private JLabel lable;

    public Listener(JLabel lable) {
        this.lable = lable;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
            Object item = e.getItemSelectable();
            JComboBox combo = null;
            if(item instanceof JComboBox) {
                combo = (JComboBox) item;
                String newLableText = lable.getText().replaceFirst("\\w+", "");
                lable.setText((String) combo.getSelectedItem() + newLableText);
            }
    }
}
