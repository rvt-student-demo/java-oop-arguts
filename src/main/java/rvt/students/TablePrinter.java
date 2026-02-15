package rvt.students;

import java.util.List;

public class TablePrinter {

    public static void print(List<student> students) {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        String[] h = {"First", "Last", "Email", "PersonalCode", "RegisteredAt"};
        int[] w = {10, 12, 28, 13, 19};

        line(w);
        row(h, w);
        line(w);

        for (student s : students) {
            row(new String[]{
                    s.getFirstName(),
                    s.getLastName(),
                    s.getEmail(),
                    s.getPersonalCode(),
                    s.getRegisteredAt()
            }, w);
        }

        line(w);
    }

    private static void line(int[] w) {
        StringBuilder sb = new StringBuilder("+");
        for (int x : w) sb.append("-".repeat(x + 2)).append("+");
        System.out.println(sb);
    }

    private static void row(String[] c, int[] w) {
        StringBuilder sb = new StringBuilder("|");
        for (int i = 0; i < c.length; i++) {
            String cell = c[i] == null ? "" : c[i];
            if (cell.length() > w[i]) cell = cell.substring(0, w[i] - 1) + "…";
            sb.append(" ").append(String.format("%-" + w[i] + "s", cell)).append(" |");
        }
        System.out.println(sb);
    }
}
