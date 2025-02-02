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
  

  private static Connection connection;

  private DB() {}

  public static Connection getConnection() throws SQLException {
    if (connection == null || connection.isClosed()) {
      connection = DriverManager.getConnection(url);
    }
    return connection;
  }
}
