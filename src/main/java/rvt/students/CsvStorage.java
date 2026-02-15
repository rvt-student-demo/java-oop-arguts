package rvt.students;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvStorage {
    private final String path;

    public CsvStorage(String path) {
        this.path = path;
    }

    public List<student> loadAll() {
        List<student> list = new ArrayList<>();
        File f = new File(path);
        if (!f.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                student s = student.fromCsv(line);
                if (s != null) list.add(s);
            }
        } catch (IOException e) {
            System.out.println("CSV read error: " + e.getMessage());
        }
        return list;
    }

    public void saveAll(List<student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (student s : students) {
                bw.write(s.toCsv());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("CSV write error: " + e.getMessage());
        }
    }
}
