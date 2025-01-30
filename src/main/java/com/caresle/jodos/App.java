package com.caresle.jodos;
import java.sql.*;

public class App {
    public static void main(String[] args) {
      DB.query("select * from tbl_jd_todos");
    }
}
