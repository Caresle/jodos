package com.caresle.jodos;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    static int LIST = 1;
    static int CREATE = 2;
    static int EDIT = 3;
    static int COMPLETE = 4;
    static int DELETE = 5;
    static int EXIT = 6;

    public static void print() {
        System.out.println("=\tSELECT THE OPTION\t=");
        System.out.println("1) List all todos");
        System.out.println("2) Create a new todo");
        System.out.println("3) Edit a todo");
        System.out.println("4) Mark a todo as completed");
        System.out.println("5) Delete a todo");
        System.out.println("6) Exit");
    }

    public static void listTodos() {
        ArrayList<Todo> todos = Todo.all();

        for (Todo todo: todos) {
            System.out.println(todo);
        }
    }

    public static void createTodo(Scanner scanner) {
        System.out.println("Enter the todo:");
        scanner.nextLine();
        String todo = scanner.nextLine();
        try {
            Todo newTodo = new Todo();
            newTodo.set("name", todo);
            newTodo.set("completed", 0);
            newTodo.save();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    public static void editTodo(Scanner scanner) {
        System.out.println("Enter the id of the todo to edit:");
        long id = scanner.nextLong();
        Todo todo = Todo.findById(id);
        System.out.println("Enter the new todo:");
        scanner.nextLine();
        String todoName = scanner.nextLine();
        todo.set("name", todoName);
        try {
            todo.save();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    public static void completeTodo(Scanner scanner) {
        System.out.println("Enter the id of the todo to complete:");
        long id = scanner.nextLong();
        Todo todo = Todo.findById(id);
        todo.set("completed", 1);
        try {
            todo.save();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    public static void deleteTodo(Scanner scanner) {
        System.out.println("Enter the id of the todo to delete:");
        long id = scanner.nextLong();
        Todo todo = Todo.findById(id);
        try {
            todo.delete();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }
}
