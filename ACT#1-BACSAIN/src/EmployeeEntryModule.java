import javax.swing.*;
import java.awt.GridLayout;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class EmployeeEntryModule {
    private DBConnector db;
    private JFrame frame;
    private JTextField fnameField, lnameField, emailField;
    private JPasswordField passwordField;
    private JButton submitButton;

    private DBConnector getDB() {
        if (db == null) {
            db = new DBConnector();
        }
        return db;
    }

    public void show() {
        frame = new JFrame("EMPLOYEE DATA ENTRY MODULE");
        frame.setSize(250, 250);
        fnameField = new JTextField(20);
        lnameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        submitButton = new JButton("SUBMIT");

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.add(new JLabel("Enter First Name: "));
        panel.add(fnameField);
        panel.add(new JLabel("Enter Last Name"));
        panel.add(lnameField);
        panel.add(new JLabel("Enter Email: "));
        panel.add(emailField);
        panel.add(new JLabel("Enter Password: "));
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(submitButton);

        frame.setLocationRelativeTo(null);
        frame.setTitle("EMPLOYEE REGISTRY");
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        submitButton.addActionListener(e -> {
            String first_name = fnameField.getText();
            String last_name = lnameField.getText();
            String email = emailField.getText();
            char[] password = passwordField.getPassword();

            try {
                if (Utils.isBlank(first_name) || Utils.isBlank(last_name) || Utils.isBlank(email)) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all required fields.");
                    return;
                }

                if (!Utils.isValidEmail(email)) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid email address.");
                    return;
                }

                if (!Utils.isValidPassword(password)) {
                    JOptionPane.showMessageDialog(frame, "Password must be at least 8 characters.");
                    return;
                }

                String encodedPassword = PasswordHasher.hash(password);

                String sql = "INSERT INTO employees (first_name, last_name, email, password) values (?, ?, ?,?)";
                try (PreparedStatement stmt = getDB().conn.prepareStatement(sql)) {
                    stmt.setString(1, first_name.trim());
                    stmt.setString(2, last_name.trim());
                    stmt.setString(3, email.trim());
                    stmt.setString(4, encodedPassword);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(frame, "Employee added successfully!");
                    passwordField.setText("");
                }
            } catch (SQLException ex) {
                System.out.println("Database error: " + ex.getMessage());
                JOptionPane.showMessageDialog(frame, "Unable to save employee. Please try again.");
            } finally {
                Arrays.fill(password, '\0');
                try {
                    getDB().conn.close();
                } catch (SQLException ignored) {
                }
            }
        });
    }
}