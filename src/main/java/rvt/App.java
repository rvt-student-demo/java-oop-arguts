package rvt;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TodoList todoList = new TodoList();
            UserInterface ui = new UserInterface(todoList);
            ui.start();
        });
    }
}