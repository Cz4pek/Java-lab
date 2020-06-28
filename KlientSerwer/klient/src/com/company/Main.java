package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main extends JFrame {

    private JPanel panel;
    private JTextArea communicationState;
    private JTextField textMessage;
    private JButton send;

    private Socket gniazdo;
    private InetAddress adres = null;

    public Main(String title) throws HeadlessException {
        super(title);
        setPreferredSize(new Dimension(400, 400));
        panel = (JPanel) getContentPane();
        try {
            adres = InetAddress.getByName("localhost");
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
        communicationState.setEditable(false);
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
            Thread msg = new Thread(() -> {
                try {
                    sendMessage();
                } catch (IOException ioException) {
                    ioException.getLocalizedMessage();
                }
            });
            msg.start();
        });
        layout.putConstraint(SpringLayout.WEST, send, 154, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, send, 20, SpringLayout.SOUTH, textMessage);

        setVisible(true);
        pack();

    }


    public static void main(String[] args) throws InterruptedException {
        Main klient = new Main("okno klienta ");
        Thread listen = new Thread(() -> {
            while (true) {
                try {
                    klient.recive();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        listen.start();
    }

    private void sendMessage() throws IOException {
        //gniazdo = new Socket(adres, 8080);
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(gniazdo.getOutputStream()));
        System.out.println("wysyłam wiadomość z klienta");
        output.write(textMessage.getText());
        communicationState.append("Klient: " + textMessage.getText() + "\n");
        System.out.println("wysłałem wiadomość: " + textMessage.getText());
        System.out.println("wysyłanie zakończone");
        output.close();
       // gniazdo.close();
    }

    public void recive() throws IOException {

            gniazdo = new Socket(adres, 8080);
            BufferedReader we = null;
            try{
                we = new BufferedReader(new InputStreamReader(gniazdo.getInputStream()));
                String wiersz="a";
                System.out.println("Rozpoczynam odbieranie wiadomości");
                while ( (wiersz = we.readLine()) != null) {
                    System.out.println("Odbieram wiadomość");
                    communicationState.append("Serwer: " + wiersz + "\n");
                }
                System.out.println("Wiadomość została odebrana");
            }
            finally {
                we.close();
            }

    }
}

