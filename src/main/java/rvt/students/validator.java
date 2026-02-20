package rvt.students;

public class Validator {

    private static final String NAME = "^(?U)\\p{L}{3,}$";
    private static final String EMAIL = "^(?U)[\\p{L}0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String PERSONAL_CODE = "^\\d{6}-\\d{5}$";
    private static String clean(String s) {
    if (s == null) return null;
    return s.replace("\u0000", "").replaceAll("\\p{Cc}", "").trim();
}

    public static boolean nameOk(String s) {
    s = clean(s);
    return s != null && s.matches(NAME);
}

public static boolean emailOk(String s) {
    s = clean(s);
    return s != null && s.matches(EMAIL);
}

public static boolean codeOk(String s) {
    s = clean(s);
    return s != null && s.matches(PERSONAL_CODE);
}
}