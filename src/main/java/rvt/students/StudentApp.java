package rvt.students;

import java.util.Scanner;

public class StudentApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StudentService service = new StudentService(new CsvStorage("data/students.csv"));

        while (true) {
            System.out.println("\nCommands: register | show | remove | edit | exit");
            System.out.print("> ");
            String cmd = sc.nextLine().trim().toLowerCase();

            if (cmd.equals("register")) {
                System.out.print("First name: ");
                String first = sc.nextLine().trim();

                System.out.print("Last name: ");
                String last = sc.nextLine().trim();

                System.out.print("Email: ");
                String email = sc.nextLine().trim();

                System.out.print("Personal code (010203-12345): ");
                String code = sc.nextLine().trim();

                String err = service.register(first, last, email, code);
                System.out.println(err == null ? "Registered!" : "Error: " + err);

            } else if (cmd.equals("show")) {
                TablePrinter.print(service.all());

            } else if (cmd.equals("remove")) {
                System.out.print("Personal code to remove: ");
                String code = sc.nextLine().trim();
                System.out.println(service.remove(code) ? "Removed!" : "Student not found.");

            } else if (cmd.equals("edit")) {
                System.out.print("Personal code to edit: ");
                String code = sc.nextLine().trim();

                System.out.print("New first name: ");
                String first = sc.nextLine().trim();

                System.out.print("New last name: ");
                String last = sc.nextLine().trim();

                System.out.print("New email: ");
                String email = sc.nextLine().trim();

                String err = service.edit(code, first, last, email);
                System.out.println(err == null ? "Edited!" : "Error: " + err);

            } else if (cmd.equals("exit")) {
                System.out.println("Bye!");
                break;

            } else {
                System.out.println("Unknown command.");
            }
        }

        sc.close();
    }
}
