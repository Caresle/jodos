package com.caresle.jodos;

import java.sql.*;

/**
 * DB
 */
public class DB {
  protected static String url = "jdbc:postgresql://localhost:5432/jodo_db?user=jodo&password=jodo";
  
  public static void query(String query) {
    try {
      Connection conn = DriverManager.getConnection(url);

      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(query);
      
      while (rs.next()) {
        System.out.println("It's working"); 
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
