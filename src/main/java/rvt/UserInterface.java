package rvt;

import java.util.Scanner;

public class UserInterface {
    private final TodoList list;
    private final Scanner scanner;

    public UserInterface(TodoList list, Scanner scanner) {
        this.list = list;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.println("Command:");
            String command = scanner.nextLine();

            if (command.equals("stop")) {
                break;
            }

            if (command.equals("add")) {
                System.out.println("To add:");
                String task = scanner.nextLine();
                list.add(task);
            } else if (command.equals("list")) {
                list.print();
            } else if (command.equals("remove")) {
                System.out.println("Which one is removed?");
                String numberText = scanner.nextLine();
                int number = Integer.parseInt(numberText);
                list.remove(number);
            }
        }
    }
}
