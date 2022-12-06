/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servermail;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author daniel
 */
public class SendMail {

    private String host;
    private int port;
    private BufferedReader entrada;
    private DataOutputStream salida;
    private Socket socket;

    public SendMail(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void initConnection() {

        try {
            String command;
            socket = new Socket(host, port);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new DataOutputStream(socket.getOutputStream());
            if (socket != null && entrada != null && salida != null) {
                System.out.println("S : " + entrada.readLine());
                command = "EHLO " + host + "\r\n";
                executeCommand(command, 'm');
            }

        } catch (Exception e) {
            System.out.println("S: No se pudo conectar al servidor");
        }

    }

    public void sendMailTo(String trasmitter, String receiver, String subject, String message) {
        String command;
        try {
            if (socket != null && entrada != null && salida != null) {
                command = "MAIL FROM: <" + trasmitter + ">" + "\r\n";
                executeCommand(command, 'l');
                command = "RCPT TO: <" + receiver + ">" + "\r\n";
                executeCommand(command, 'l');
                command = "DATA" + "\r\n";
                executeCommand(command, 'l');
                command = "Subject: " + subject + "\r\n" + message + "\r\n" + "." + "\r\n";
                executeCommand(command, 'l');
            }
        } catch (Exception e) {
            System.out.println("S: Connection con el servidor perdida");
        }

    }

    public void closeConnection() {
        try {
            String command = "QUIT\r\n";
            executeCommand(command, 'l');
            salida.close();
            entrada.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("S: Connection con el servidor perdida");
        }

    }

    private void executeCommand(String command, char type) {
        try {
            System.out.println("C: " + command);
            salida.writeBytes(command);
            if (type == 'm') {
                System.out.println("S: " + getMultiline(entrada));
            } else {
                System.out.println("S: " + entrada.readLine());
            }

        } catch (Exception e) {
            System.out.println("S: Connection con el servidor perdida");
        }

    }

    static protected String getMultiline(BufferedReader in) throws IOException {
        String lines = "";
        while (true) {
            String line = in.readLine();
            if (line == null) {
                throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.charAt(3) == ' ') {
                lines = lines + "\n" + line;
                break;
            }
            lines = lines + "\n" + line;
        }
        return lines;
    }

}
