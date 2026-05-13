import javax.swing.*;
import java.awt.GridLayout;
import java.sql.PreparedStatement;

public class EmployeeEntryModule {
    private DBConnector db = new DBConnector();
    private JFrame frame;
    private JTextField fnameField, lnameField, emailField;
    private JPasswordField passwordField;
    private JButton submitButton;

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

            String sql = "INSERT INTO employees (first_name, last_name, email, password) values (?, ?, ?,?)";
            try {
                PreparedStatement stmt = db.conn.prepareStatement(sql);
                stmt.setString(1, first_name);
                stmt.setString(2, last_name);
                stmt.setString(3, email);
                stmt.setString(4, Utils.hashPassword(new String(password)));
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(frame, "Employee added successfully!");

                stmt.close();
                db.conn.close();
            } catch (java.sql.SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage());
                System.out.println("Database error: " + ex.getMessage());
            }
        });
    }
}