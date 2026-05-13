package src;

import javax.swing.*;
import java.util.Arrays;

public final class AccessGate {
    private static final String ADMIN_CODE_ENV = "ACT1_ADMIN_CODE";

    private AccessGate() {}

    public static Role promptRole(JFrame parent) {
        Object[] options = {"ADMIN", "EMPLOYEE", "USER"};
        int choice = JOptionPane.showOptionDialog(
                parent,
                "Select your role:",
                "Role Selection",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        if (choice < 0) return null;
        return switch (choice) {
            case 0 -> Role.ADMIN;
            case 1 -> Role.EMPLOYEE;
            case 2 -> Role.USER;
            default -> null;
        };
    }

    public static boolean promptAdminCode(JFrame parent) {
        // Load environment variables from .env file if available
        EnvLoader.load();
        
        String expected = EnvLoader.get(ADMIN_CODE_ENV);
        if (expected == null || expected.isBlank()) {
            expected = "admin";
            JOptionPane.showMessageDialog(parent,
                    "Warning: " + ADMIN_CODE_ENV + " is not set.\n" +
                    "Using default admin code: 'admin'.\n" +
                    "Set the environment variable to change it.",
                    "Security Notice",
                    JOptionPane.WARNING_MESSAGE);
        }

        JPasswordField pf = new JPasswordField();
        int ok = JOptionPane.showConfirmDialog(parent, pf, "Enter Admin Access Code", JOptionPane.OK_CANCEL_OPTION);
        if (ok != JOptionPane.OK_OPTION) return false;

        char[] entered = pf.getPassword();
        try {
            return constantTimeEquals(entered, expected.toCharArray());
        } finally {
            Arrays.fill(entered, '\0');
        }
    }

    private static boolean constantTimeEquals(char[] a, char[] b) {
        int diff = a.length ^ b.length;
        int min = Math.min(a.length, b.length);
        for (int i = 0; i < min; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }
}
