package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main extends JFrame {

    private JPanel panel;
    private JTextArea communicationState;
    private JTextField textMessage;
    private JButton send;

    public ServerSocket serwer;
    public Socket g;


    public Main(String title) throws HeadlessException {
        super(title);
        setPreferredSize(new Dimension(400, 400));
        panel = (JPanel) getContentPane();
        try {
            serwer = new ServerSocket(8080);

        } catch (IOException e) {
            e.printStackTrace();
        }


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        panel.setBackground(Color.pink);

        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);

        communicationState = new JTextArea();
        communicationState.setPreferredSize(new Dimension(300, 200));
        //communicationState.setEditable(false);
        panel.add(communicationState);
        layout.putConstraint(SpringLayout.WEST, communicationState, 42, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, communicationState, 20, SpringLayout.NORTH, panel);

        textMessage = new JTextField();
        textMessage.setPreferredSize(new Dimension(300, 25));
        panel.add(textMessage);
        layout.putConstraint(SpringLayout.WEST, textMessage, 42, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, textMessage, 20, SpringLayout.SOUTH, communicationState);

        send = new JButton("send");
        send.setPreferredSize(new Dimension(76, 25));
        panel.add(send);
        send.addActionListener((ActionEvent e) -> {
            Thread msg2 = new Thread(() -> {
                try {
                    sendMessage();
                } catch (IOException ioException) {
                    ioException.getLocalizedMessage();
                }
            });
            msg2.start();
        });
        layout.putConstraint(SpringLayout.WEST, send, 154, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, send, 20, SpringLayout.SOUTH, textMessage);

        setVisible(true);
        pack();

    }


    public static void main(String[] args) {
        Main sv = new Main("okno serwera");
        Thread listen = new Thread(() -> {
            while (true) {
                try {
                    sv.recive();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        listen.start();
    }

    private void sendMessage() throws IOException {
        //Socket g = serwer.accept();
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(g.getOutputStream()));
        System.out.println("wysyłam wiadomość z klienta");
        output.write(textMessage.getText());
        communicationState.append("Serwer: " + textMessage.getText() + "\n");
        System.out.println("wysłałem wiadomość: " + textMessage.getText());
        System.out.println("wysyłanie zakończone");
        output.close();
    }


    public void recive() throws IOException {
        g = serwer.accept();
        BufferedReader we = new BufferedReader(new InputStreamReader(g.getInputStream()));
        String wiersz="a";
        System.out.println("Rozpoczynam odbieranie wiadomości");
        while ( (wiersz = we.readLine()) != null) {
            System.out.println("Odbieram wiadomość");
            communicationState.append("Klient: " + wiersz + "\n");
        }
        System.out.println("Wiadomość została odebrana");
        we.close();
    }



}


