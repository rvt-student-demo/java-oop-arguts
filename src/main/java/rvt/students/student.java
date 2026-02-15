package rvt.students;

public class student {
    private String firstName;
    private String lastName;
    private String email;
    private String personalCode;
    private String registeredAt;

    public student(String firstName, String lastName, String email, String personalCode, String registeredAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.personalCode = personalCode;
        this.registeredAt = registeredAt;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPersonalCode() { return personalCode; }
    public String getRegisteredAt() { return registeredAt; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }

    public String toCsv() {
        return firstName + "," + lastName + "," + email + "," + personalCode + "," + registeredAt;
    }

    public static student fromCsv(String line) {
        String[] p = line.split(",", -1);
        if (p.length != 5) return null;
        return new student(p[0], p[1], p[2], p[3], p[4]);
    }
}
