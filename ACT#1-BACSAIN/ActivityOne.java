/*BCS323L-OCa Baseline activity 
    Name: BACSAIN, Earl Lawrence P.
    Date: February 4, 2026
    TASKS: 
        1. Create a Data Entry Module for Employee Information and User Information.
        2. Password modules for both Employee and User Information.    
 */

import javax.swing.*;
import java.awt.event.*;
import java.sql.PreparedStatement;


class ActivityOne {

    //instance of the DBConenctor
    DBConnector db = new DBConnector();
    JFrame frame;
    JTextField fnameField, lnameField, emailField;
    JPasswordField passwordField;
    JButton submitButton;

    public void EmployeeEntryModule() {
        frame = new JFrame("EMPLOYEE DATA ENTRY MODULE");
        fnameField = new JTextField(20);
        lnameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        submitButton = new JButton("SUBMIT");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter First Name: "));
        panel.add(fnameField);
        panel.add(new JLabel("Enter Last Name"));
        panel.add(lnameField);
        panel.add(new JLabel("Enter Email: "));
        panel.add(emailField);
        panel.add(new JLabel("Enter Password: "));
        panel.add(passwordField);
        panel.add(submitButton);

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        submitButton.addActionListener(e -> {
            String first_name = fnameField.getText();
            String last_name = lnameField.getText();
            String email = emailField.getText();
            char[] password = passwordField.getPassword();
        
            String sql = "INSERT INTO employees (first_name, last_name, email, password) (?, ?, ?,?)";
            try {
                PreparedStatement stmt = db.conn.prepareStatement(sql);
                stmt.setString(1, first_name);
                stmt.setString(2, last_name);
                stmt.setString(3, email);
                stmt.setString(4, new String(password));
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> new ActivityOne().EmployeeEntryModule());
    }
}
