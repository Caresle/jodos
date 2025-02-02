package com.caresle.jodos;

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
}
