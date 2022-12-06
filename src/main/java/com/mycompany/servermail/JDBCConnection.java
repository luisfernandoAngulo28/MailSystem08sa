package com.mycompany.servermail;

import java.sql.*;
import java.util.Properties;

/**
 *
 * @author daniel
 */
public class JDBCConnection {

    private String host;
    private int port;
    private String database;
    private String user;
    private String password;
    private Connection conn;
    private Statement st;

    public JDBCConnection(String host, int port, String database, String user, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    public void initConnection() {
        try {
            String url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
            Properties props = new Properties();
            props.setProperty("user", user);
            props.setProperty("password", password);
            props.setProperty("ssl", "false");
            conn = DriverManager.getConnection(url, props);
            st = conn.createStatement();

        } catch (Exception e) {
            System.out.println("Ocurrio un error durante la conexion");
        }
    }

    public void showQuery(ResultSet rs, String[] columname) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();

            int colCount = rsmd.getColumnCount();
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
                System.out.println("Persona " + rowCount);
                for (int i = 1; i <= colCount; i++) {
                    System.out.println(columname[i - 1] + ": " + rs.getString(i));
                }
            }
        } catch (Exception e) {
            System.out.println("Error durante la lectura de datos");

        }

    }

    public ResultSet query(String query) {
        ResultSet rs = null;
        try {
            rs = st.executeQuery(query);
        } catch (Exception e) {
            System.out.println("Ocurrio un erro al intentar realizar la consulta");
        }
        return rs;
    }

    public void queryCUD(String query) {

        try {
            st.executeUpdate(query);
            
        } catch (Exception e) {
            System.out.println("Ocurrio un erro al intentar realizar la modificacion");
        }
    }

    public void closeConnection() {
        try {
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error al intentar cerrar");
        }

    }
}
