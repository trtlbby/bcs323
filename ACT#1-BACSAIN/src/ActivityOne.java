/*BCS323L-OCa Baseline activity
    Name: BACSAIN, Earl Lawrence P.
    Date: February 4, 2026
    TASKS:
        1. Create a Data Entry Module for Employee Information and User Information.
        2. Password modules for both Employee and User Information.
        3. Add basic role-based access control (RBAC) for module access.
 */

import javax.swing.*;
import java.awt.GridLayout;

public class ActivityOne {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Role role = AccessGate.promptRole(null);
            if (role == null) {
                return;
            }

            boolean isAdmin = role == Role.ADMIN && AccessGate.promptAdminCode(null);

            JFrame chooserFrame = new JFrame("Choose Entry Module");
            chooserFrame.setSize(500, 500);
            chooserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            chooserFrame.setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridLayout(2, 1, 20, 20));
            JButton employeeBtn = new JButton("Employee Entry");
            JButton userBtn = new JButton("User Entry");

            employeeBtn.setEnabled(isAdmin);
            userBtn.setEnabled(isAdmin);

            employeeBtn.addActionListener(e -> {
                if (!isAdmin) {
                    JOptionPane.showMessageDialog(chooserFrame, "Access denied. Admin role required.");
                    return;
                }
                chooserFrame.dispose();
                new EmployeeEntryModule().show();
            });

            userBtn.addActionListener(e -> {
                if (!isAdmin) {
                    JOptionPane.showMessageDialog(chooserFrame, "Access denied. Admin role required.");
                    return;
                }
                chooserFrame.dispose();
                new UserEntryModule().show();
            });

            panel.add(employeeBtn);
            panel.add(userBtn);

            chooserFrame.add(panel);

            if (!isAdmin) {
                JOptionPane.showMessageDialog(chooserFrame, "Role selected: " + role + "\nAdmin access required to open modules.");
            }

            chooserFrame.setVisible(true);
        });
    }
}
