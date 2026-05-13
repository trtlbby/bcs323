public class Utils {
    public static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        String e = email.trim();
        // Basic email check (good enough for classroom demos)
        return e.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }

    public static boolean isValidPassword(char[] password) {
        return password != null && password.length >= 8;
    }
}
