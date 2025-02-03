package com.caresle.jodos;

import java.util.ArrayList;

/**
 * Todo
 */
public class Todo extends Model {
  static {
    table = "tbl_jd_todos";
  }

  public Todo() {}

  public static Todo findById(long id) {
    return (Todo) Model.findById(Todo.class, id);
  }

  public static ArrayList<Todo> all() {
    return (ArrayList<Todo>) Model.all(Todo.class);
  }
}
