package com.caresle.jodos;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Todo
 */
public class Todo {
  protected int id;
  protected String name;
  protected Boolean completed;
  protected String createadAt;

  public Todo(ResultSet entity) {
    try {
      this.id = Integer.parseInt(entity.getString("id"));
      this.name = entity.getString("name");
      this.completed = Integer.parseInt(entity.getString("completed")) == 1;
      this.createadAt = entity.getString("created_at");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  } 

  @Override
  public String toString() {
    return this.id + " " + this.name + " " + this.completed + " " + this.createadAt;
  }
}
