package rvt.students;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StudentService {
    private final CsvStorage storage;
    private final List<student> students;

    public StudentService(CsvStorage storage) {
        this.storage = storage;
        this.students = storage.loadAll();
    }

    public List<student> all() {
        return students;
    }

    public student findByCode(String code) {
        for (student s : students) {
            if (s.getPersonalCode().equals(code)) return s;
        }
        return null;
    }

    public String register(String first, String last, String email, String code) {
        if (!validator.nameOk(first)) return "First name: only letters, min 3.";
        if (!validator.nameOk(last)) return "Last name: only letters, min 3.";
        if (!validator.emailOk(email)) return "Email is not valid.";
        if (!validator.codeOk(code)) return "Personal code must be like 010203-12345.";
        if (findByCode(code) != null) return "Personal code already exists.";

        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        students.add(new student(first, last, email, code, now));
        storage.saveAll(students);
        return null;
    }

    public boolean remove(String code) {
        student s = findByCode(code);
        if (s == null) return false;
        students.remove(s);
        storage.saveAll(students);
        return true;
    }

    public String edit(String code, String newFirst, String newLast, String newEmail) {
        student s = findByCode(code);
        if (s == null) return "Student not found.";

        if (!validator.nameOk(newFirst)) return "First name: only letters, min 3.";
        if (!validator.nameOk(newLast)) return "Last name: only letters, min 3.";
        if (!validator.emailOk(newEmail)) return "Email is not valid.";

        s.setFirstName(newFirst);
        s.setLastName(newLast);
        s.setEmail(newEmail);

        storage.saveAll(students);
        return null;
    }
}
