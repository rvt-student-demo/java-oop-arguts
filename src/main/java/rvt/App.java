package rvt;

public class App {
    public static void main(String[] args) {
        TodoList list = new TodoList();
        UserInterface ui = new UserInterface(list);
        ui.start();
    }
}