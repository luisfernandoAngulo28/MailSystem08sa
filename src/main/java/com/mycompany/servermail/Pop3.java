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
public class Pop3 {

    private String host;
    private int port;
    private String user;
    private String password;
    private BufferedReader entrada;
    private DataOutputStream salida;
    private Socket socket;

    public Pop3(String host,int port, String user,String password) {
        
        this.host=host;
        this.port=port;
        this.user=user;
        this.password=password;
        
    }

    
    public void initConnection() {

        try {
            String command;
            socket = new Socket(host, port);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new DataOutputStream(socket.getOutputStream());
            if (socket != null && entrada != null && salida != null) {
                System.out.println("S : " + entrada.readLine());
                command = "USER " + user + "\r\n";
                executeCommand(command, 'l');
                command = "PASS " + password + "\r\n";
                executeCommand(command, 'l');
            }

        } catch (Exception e) {
            System.out.println("S: No se pudo conectar al servidor");
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


    static protected String getMultiline(BufferedReader in) throws IOException {
        String lines = "";
        while (true) {
            String line = in.readLine();
            if (line == null) {
// Server closed connection
                throw new IOException(" S : Server unawares closed theconnection.");
            }
            if (line.equals(".")) {
// No more lines in the server response
                break;
            }
            if ((line.length() > 0) && (line.charAt(0) == '.')) {
// The line starts with a "." - strip it off.
                line = line.substring(1);
            }
// Add read line to the list of lines
            lines = lines + "\n" + line;
        }
        return lines;
    }
    
    
    
         
    
    public String getSubject(int retr){
        String result = "";
        initConnection();
        String command = "RETR " +retr +"\r\n" ; 
        try {
            salida.writeBytes(command);
            String value ="";
            boolean bandera = false;
        while (true) {
            String line = entrada.readLine();
            if (line == null) {

                throw new IOException(" S : Server unawares closed theconnection.");
            }

            bandera = bandera && !line.contains("User-Agent:");
            if (line.contains("Subject:") || bandera){
                bandera = true;
                String aux[] = line.split("(Subject:)\\s*");
                for (int i = 0; i < aux.length; i++) {
                    System.out.println(aux[i] +" la cant es "+aux.length );
                    if (aux[i]!="")
                        value=value + aux[i];
                }
            }
            if (line.contains("User-Agent:")){
                result = value;
                break;
            }
            
        }  

        } catch (Exception e) {
            System.out.println("S: Connection con el servidor perdida");
        }       
        
        closeConnection();
    
    return result;
    }
    
    public int getCantToMessages(){
        int result= 0;
        initConnection();
        String command = "STAT "+"\r\n" ;
        try {
            salida.writeBytes(command);
            String aux = entrada.readLine();
            String aux2[]=aux.split("[\\s]");
            result = Integer.parseInt(aux2[1]);

        } catch (Exception e) {
            System.out.println("Error durante la consulta");
        }
        
        closeConnection();
        return result;
    }
    
}
