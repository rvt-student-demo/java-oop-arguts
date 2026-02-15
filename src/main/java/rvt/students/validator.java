package rvt.students;

public class validator {
    private static final String NAME = "^[\\p{L}]{3,}$";
    private static final String EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String PERSONAL_CODE = "^\\d{6}-\\d{5}$"; // 010203-12345

    public static boolean nameOk(String s) { return s != null && s.matches(NAME); }
    public static boolean emailOk(String s) { return s != null && s.matches(EMAIL); }
    public static boolean codeOk(String s) { return s != null && s.matches(PERSONAL_CODE); }
}
