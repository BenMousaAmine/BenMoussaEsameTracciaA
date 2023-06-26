// CustomFrame.java

package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class CustomFrame extends JFrame {
    private JTextArea textArea;
    private HandleFrameRequest handleFrameRequest;

    private void handleBtnRequest(List<Hotel> hotelList) {
        String result = handleFrameRequest.transformToJson(hotelList);
        updateTextArea(result);
        String response = null;
        try {
            response = handleFrameRequest.receiveResponse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        updateTextArea(response);
    }

    private void updateTextArea(String text) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                textArea.setText(text);
            }
        });
    }

    private void newConnection() {
        handleFrameRequest.closeConnections();
        Socket clientSocket = null;
        try {
            clientSocket = new Socket("172.16.150.148", 1234);
            System.out.println("New connection");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        handleFrameRequest = new HandleFrameRequest(clientSocket);
    }

    public CustomFrame(Socket clientSocket) {
        setTitle("Hotel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        handleFrameRequest = new HandleFrameRequest(clientSocket);

        JButton btnExpensive = new JButton("Most Expensive");
        JButton btnAll = new JButton("All");
        JButton btnSorted = new JButton("Sorted by Name");
        JButton btnExit = new JButton("EXIT");
        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        JLabel label = new JLabel("Data in JSON Format:");
        container.add(label, BorderLayout.PAGE_START);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnExpensive);
        buttonPanel.add(btnAll);
        buttonPanel.add(btnSorted);
        buttonPanel.add(btnExit);

        container.add(buttonPanel, BorderLayout.PAGE_END);
        container.add(scrollPane, BorderLayout.CENTER);

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFrameRequest.closeConnection();
                System.out.println("Connection closed");
                dispose(); // Chiude l'interfaccia grafica
            }
        });

        btnExpensive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Hotel> hotelList = handleFrameRequest.getMostExpensiveSuite();
                handleBtnRequest(hotelList);
                newConnection();
            }
        });

        btnAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Hotel> hotelList = handleFrameRequest.getHotelList();
                handleBtnRequest(hotelList);
                newConnection();
            }
        });

        btnSorted.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Hotel> hotelList = handleFrameRequest.getCarsSortedByName();
                handleBtnRequest(hotelList);
                newConnection();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Socket clientSocket = new Socket("172.16.150.148", 1234);
                    CustomFrame frame = new CustomFrame(clientSocket);
                    frame.setSize(400, 300);
                    frame.setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
