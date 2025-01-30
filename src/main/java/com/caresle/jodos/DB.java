package com.caresle.jodos;

import java.sql.*;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * DB
 */
public class DB {
  static String url;

  static {
    Dotenv dotenv = Dotenv.load();
    String port = dotenv.get("DB_PORT");
    String database = dotenv.get("POSTGRES_DB");
    String username = dotenv.get("POSTGRES_USER");
    String password = dotenv.get("POSTGRES_PASSWORD");
    url = "jdbc:postgresql://localhost:" + port + "/" + database + "?user=" + username + "&password=" + password; 
  } 
  
  public static <T> void query(String query) {
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
