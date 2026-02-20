package rvt.students;

import rvt.utils.ConsoleColors;

import java.io.File;
import java.util.Scanner;

public class StudentApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        
        String workDir = System.getProperty("user.dir");

        
        String csvPath = workDir + File.separator + "java-oop-arguts"
                + File.separator + "data"
                + File.separator + "students.csv";

        System.out.println(ConsoleColors.YELLOW.getCode() + "WORKDIR: " + workDir + ConsoleColors.RESET.getCode());
        System.out.println(ConsoleColors.YELLOW.getCode() + "CSV PATH: " + csvPath + ConsoleColors.RESET.getCode());

        StudentService service = new StudentService(new CsvStorage(csvPath));

        while (true) {
            System.out.println(
                    ConsoleColors.BLUE.getCode() +
                            "\nCommands: register | show | remove | edit | exit" +
                            ConsoleColors.RESET.getCode()
            );
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

                if (err == null) {
                    System.out.println(
                            ConsoleColors.GREEN.getCode() +
                                    "Student registered successfully!" +
                                    ConsoleColors.RESET.getCode()
                    );
                } else {
                    System.out.println(
                            ConsoleColors.RED.getCode() +
                                    "Error: " + err +
                                    ConsoleColors.RESET.getCode()
                    );
                }

            } else if (cmd.equals("show")) {
                TablePrinter.print(service.all());

            } else if (cmd.equals("remove")) {
                System.out.print("Personal code to remove: ");
                String code = sc.nextLine().trim();

                if (service.remove(code)) {
                    System.out.println(
                            ConsoleColors.GREEN.getCode() +
                                    "Student removed!" +
                                    ConsoleColors.RESET.getCode()
                    );
                } else {
                    System.out.println(
                            ConsoleColors.RED.getCode() +
                                    "Student not found." +
                                    ConsoleColors.RESET.getCode()
                    );
                }

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

                if (err == null) {
                    System.out.println(
                            ConsoleColors.GREEN.getCode() +
                                    "Edited!" +
                                    ConsoleColors.RESET.getCode()
                    );
                } else {
                    System.out.println(
                            ConsoleColors.RED.getCode() +
                                    "Error: " + err +
                                    ConsoleColors.RESET.getCode()
                    );
                }

            } else if (cmd.equals("exit")) {
                System.out.println(
                        ConsoleColors.YELLOW.getCode() +
                                "Bye!" +
                                ConsoleColors.RESET.getCode()
                );
                break;

            } else {
                System.out.println(
                        ConsoleColors.RED.getCode() +
                                "Unknown command." +
                                ConsoleColors.RESET.getCode()
                );
            }
        }

        sc.close();
    }
}