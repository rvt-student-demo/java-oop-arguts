package rvt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private final ArrayList<String> tasks;S
    private final String filePath;

    public TodoList() {
        this.filePath = "data/todo.csv";
        this.tasks = new ArrayList<>();
        loadFromFile();
    }

    private void loadFromFile() {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            return;
        }

        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i).trim();
                if (line.isEmpty()) {
                    continue;
                }
                if (i == 0 && line.toLowerCase().startsWith("id,task")) {
                    continue;
                }
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    tasks.add(parts[1]);
                }
            }
        } catch (IOException e) {
            // Ignore load errors for now.
        }
    }

    private void saveToFile() {
        Path path = Paths.get(filePath);
        List<String> lines = new ArrayList<>();
        lines.add("id,task");
        for (int i = 0; i < tasks.size(); i++) {
            lines.add((i + 1) + "," + tasks.get(i));
        }
        try {
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            // Ignore save errors for now.
        }
    }

    public void add(String task) {
        tasks.add(task);
        saveToFile();
    }

    public void remove(int number) {
        tasks.remove(number - 1);
        saveToFile();
    }

    public void print() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ": " + tasks.get(i));
        }
    }
}
