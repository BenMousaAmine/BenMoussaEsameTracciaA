package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientHandler implements Runnable{

    Socket clientSocket;
    private PrintWriter out;

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        InetAddress address = clientSocket.getInetAddress();
        int port = clientSocket.getPort();
        System.out.println("connected: " + address + " on port: " + port);
    }

    void handle(){
        out = null;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            readLoop(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    Boolean readLoop(BufferedReader in, PrintWriter out) {
        String input = "";
        HotelRepository hr = HotelRepository.getInstance();
        try {
            while ((input = in.readLine()) != null) {
                input = input.trim();
                switch (input.toUpperCase()) {
                    case "MOST_EXPENSIVE": {
                        System.out.println(hr.moreExpSuiteToJson());
                        out.println(hr.moreExpSuiteToJson());
                        break;
                    }
                    case "ALL": {
                        System.out.println(hr.allHotelToJson());
                        out.println(hr.allHotelToJson());
                        break;
                    }
                    case "ALL_SORTED": {
                        System.out.println(hr.allSortedToJson());
                        out.println(hr.allSortedToJson());
                        break;
                    }
                    case "EXIT": {
                        System.out.println("Thank you for using our server");
                        System.out.println("Connection Done ");
                        out.println("Thank you for using our server");
                        out.println("Connection Done ");
                        return true;
                    }
                    default: {
                        System.out.println("error");
                        out.println("error");
                    }
                }
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        InetAddress address = clientSocket.getInetAddress();
        int port = clientSocket.getPort();
        System.out.println("done on: " + address + " on port: " + port);
        return false;
    }


    @Override
    public void run() {
        handle();
    }
}
