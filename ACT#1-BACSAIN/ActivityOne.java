/*BCS323L-OCa Baseline activity 
    Name: BACSAIN, Earl Lawrence P.
    Date: February 4, 2026
    TASKS: 
        1. Create a Data Entry Module for Employee Information and User Information.
        2. Password modules for both Employee and User Information.    
 */

import javax.swing.*;
import java.awt.GridLayout;
import java.sql.PreparedStatement;


class ActivityOne {

    //instance of the DBConnector
    DBConnector db = new DBConnector();
    JFrame frame;
    JTextField fnameField, lnameField, emailField;
    JPasswordField passwordField;
    JButton submitButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame chooserFrame = new JFrame("Choose Entry Module");
            chooserFrame.setSize(500, 500);
            chooserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            chooserFrame.setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridLayout(2, 1, 20, 20));
            JButton employeeBtn = new JButton("Employee Entry");
            JButton userBtn = new JButton("User Entry");

            employeeBtn.addActionListener(e -> {
                chooserFrame.dispose();
                new EmployeeEntryModule().show();
            });

            userBtn.addActionListener(e -> {
                chooserFrame.dispose();
                new UserEntryModule().show();
            });

            panel.add(employeeBtn);
            panel.add(userBtn);

            chooserFrame.add(panel);
            chooserFrame.setVisible(true);
        });
    }
}
