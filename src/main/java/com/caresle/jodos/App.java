package com.caresle.jodos;
import java.sql.*;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        //String url = "jdbc:postgresql://localhost:5432/jodos_db?user=jodo&password=jodo";
        String url = "jdbc:postgresql://localhost:5432/jodo_db";
        String user = "jodo";
        String password = "jodo";

        try {
          Class.forName("org.postgresql.Driver");
          System.out.println("Conn");
          Connection conn = DriverManager.getConnection(url, user, password);

          System.out.println("statement");
          Statement st = conn.createStatement();
          System.out.println("resulset");
          ResultSet rs = st.executeQuery("select * from tbl_jd_todos");
          
          while (rs.next()) {
            System.out.println("Column 1 returned");
            System.out.println(rs.getString(1));
          }

          rs.close();
          st.close();
          conn.close();
        } catch (Exception e) {
            if (e instanceof SQLException) {
              System.out.println("SQL Exception: " + e.getMessage());
            }

            if (e instanceof SQLTimeoutException) {
              System.out.println("SQL TIMEOUT:" + e.getMessage());
            }
            System.out.println("General: " + e.getMessage());
        }
    }
}
