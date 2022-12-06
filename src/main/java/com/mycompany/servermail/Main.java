package com.mycompany.servermail;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 *
 * @author daniel
 */
public class Main {

    public static final String HOST = "www.tecnoweb.org.bo";
    public static final int PORT = 5432;
    public static final String USER = "grupo08sa";
    public static final String PASS = "grup008grup008";
    public static final String DATABASE = "db_grupo08sa";
    private int nroDeMensajes;
    
    public void mainProcess(){
      /*  Pop3 pop = 
        int mensajesNuevos=pop.getCantToMessages() - nroDeMensajes;
        
        if (mensajesNuevos>0){
            for (int i = 0; i < mensajesNuevos; i++) {
                
            }
            
        }*/
    
    
    }
    
    

    public static void main(String args[]) {

     /*    SendMail mail = new SendMail(HOST, PORT);
        mail.initConnection();
        mail.sendMailTo("grupo08sa@tecnoweb.org.bo","fernando.fa671@gmail.com", "Estoy con luis", 
                "Hola luis , como has estado \r\n te he extrañado basura \r\n debes de lavar la ropa :V"      
                );
        
        mail.closeConnection();*/
 /*          Pop3 pop = new Pop3(HOST, PORT, USER, PASS);
                pop.initConnection();
                int ultregistrado = pop.getCantMessages();
                pop.closeConnection();
                int ultmensaje;
        while (true) {
            try {
                Thread.sleep(600 * 1000);
                pop = new Pop3(HOST, PORT, USER, PASS);
                pop.initConnection();
                ultmensaje = pop.getCantMessages();
                if (ultmensaje==-1)
                    break;
                pop.closeConnection();
            } catch (Exception e) {
                System.out.println(e);
            }
        }*/
     JDBCConnection con = new JDBCConnection(HOST, PORT, DATABASE, USER, PASS);
      con.initConnection();
      String columname[] = {"CI","Nombre","Apellidos","Profesion","Telefono","Celular","Correo"};
     // con.queryCUD("INSERT INTO PERSONA VALUES(11111,'fernando','heredia angulo','auxiliar',3898823,76688541,'luis.fe671@gmail.com');");
      con.showQuery(con.query("SELECT * FROM persona"),columname);
     con.closeConnection();
       /* String orden = "(LIS|INS|EDT|ELM)";
       // edtper[4715292]CON[sadfa,asdfas,asdf,asf]
        String regexp = "^\\s*" + orden + "PER\\[\\s*((\".*\")|(\\d+)|(\\*))(\\s*,((\".*\")|(\\d+)|(\\*)))*\\s*\\]";
        String cadena = "INSPER[\"4715292\",\"Juan Carlos\",\"Perez Seras\",\"Estudiante\",\"33554433\",\"71055123\",\"juanperez@uagrm.edu.bo\"]";
        String au = "\\s*";
        //Pattern pat = Pattern.compile("^\\s*LISPER\\["+aux+".*\\"+aux+"]");
        Pattern pat = Pattern.compile(regexp);
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {
            System.out.println("SI");
        } else {
            System.out.println("NO");
        }
       
        Pop3 pop = new Pop3(HOST, 110, USER, PASS);
        cadena = pop.getSubject(594);
        
        mat = pat.matcher(cadena);
        if (mat.matches()){
            System.out.println("La cadena es valida para procesar");
        }else{
            System.out.println("La cadena no es valida para procesar");
        }
        
        
        
        System.out.println("Este es el subject " + cadena);
        
        
        String rest="[\\[\",\\]]";
        String str = cadena;
        String[] cadenas = str.split(rest);
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < cadenas.length; i++) {
            if (cadenas[i].length()!=0){
                list.add(cadenas[i]);
                System.out.println(cadenas[i]);
            }
        }
        String ord=list.get(0);
        int ci = Integer.parseInt(list.get(1));
        String nombre = list.get(2);
        String apellido = list.get(3);
        String profesion = list.get(4);
        int telefono = Integer.parseInt(list.get(5));
        int celular = Integer.parseInt(list.get(6));
        String correo = list.get(7);*/
        
    //    Persona p = new Persona(ci, nombre, apellido, profesion, telefono, celular, correo);
        //Persona p = new Persona(list.get(1),list.get(PORT), rest, rest, PORT, PORT, orden)
       /* for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i) + "   "+i);
        }
        Pop3 pop = new Pop3(HOST, 110, USER, PASS);
        cadena = pop.getSubject(500);
        System.out.println(cadena +"    value");
        System.out.println(pop.getSubject(501) +"   sol");
        System.out.println(pop.getCantToMessages());*/
      /*  cadenas = cadena.split("(Subject:)\\s*");
        System.out.println(cadena);
        for (int i = 0; i < cadenas.length; i++) {
            if (cadenas[i].length()!=0){
                //list.add(cadenas[i]);
                System.out.println(cadenas[i]);
            }
        }*/
      
 
    
    
    }

}
