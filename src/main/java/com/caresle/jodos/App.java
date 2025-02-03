package com.caresle.jodos;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        while (option != Ui.EXIT) {
            Ui.print();
            option = scanner.nextInt();

            if (option == Ui.LIST) {
                Ui.listTodos();
                continue;
            }

            if (option == Ui.CREATE) {
                Ui.createTodo(scanner);
                continue;
            }

            if (option == Ui.EDIT) {
                Ui.editTodo(scanner);
                continue;
            }

            if (option == Ui.COMPLETE) {
                Ui.completeTodo(scanner);
                continue;
            }

            if (option == Ui.DELETE) {
                Ui.deleteTodo(scanner);
                continue;
            }
        }

        scanner.close();
    }
}
