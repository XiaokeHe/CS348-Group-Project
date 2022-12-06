import javax.swing.*;
import javax.swing.SwingUtilities;
import java.sql.SQLException;

public class Run {
    public static void main(String args[]) {
        String jdbc = "jdbc:mysql://localhost:3306/Library Managament";
        String username = "root";
        String password = "20020530hxk!";
        boolean loggedIn = false;

        while (!loggedIn) {
            LoginGUI login = new LoginGUI();
            SwingUtilities.invokeLater(login);
            while (login.getAction() == null) {
                Thread.onSpinWait();
            } // end while
            switch (login.getAction()) {
                case ("login"): {
                    String idPassword = String.format("%s|%s", login.getUsername(),
                            login.getPassword());
                    loggedIn = true;
                    if (!loggedIn) {
                        JOptionPane.showMessageDialog(null, "ID or Password is incorrect",
                                "Message Login", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Login successful",
                                "Message Login", JOptionPane.INFORMATION_MESSAGE);
                        loggedIn = true;
                    } // end if
                    break;
                }
                // create account option
                case ("create"): {
                    boolean created = false;
                    while (!created) {
                        String pw = null;
                        String name = JOptionPane.showInputDialog(null,
                                "Enter Name",
                                "Sign up", JOptionPane.QUESTION_MESSAGE);
                        if (name == null) {
                            break;
                        } // end if
                        boolean invalid = true;
                        while (pw == null || pw.equals("") ||
                                invalid) {
                            pw = JOptionPane.showInputDialog(null,
                                    "Enter password", "Sign up",
                                    JOptionPane.QUESTION_MESSAGE);
                            if (pw == null) {
                                created = true;
                                break;
                            }
                            boolean length = false;
                            boolean digit = false;
                            boolean Uppercase = false;
                            boolean Lowercase = false;
                            boolean invalidPassword = false;
                            if (pw.length() >= 8) {
                                length = true;
                                for (int i = 0; i < pw.length(); i++) {
                                    char c = pw.charAt(i);
                                    if (Character.isDigit(c)) {
                                        digit = true;
                                    } else if (Character.isUpperCase(c)) {
                                        Uppercase = true;
                                    } else if (Character.isLowerCase(c)) {
                                        Lowercase = true;
                                    } else {
                                        if (pw.contains(" ")) {
                                            invalidPassword = true;
                                        } else if (pw.contains(",")) {
                                            invalidPassword = true;
                                        } else if (pw.contains("|")) {
                                            invalidPassword = true;
                                        } // end if
                                    } // end if
                                } // end for
                            } // end if
                            if (invalidPassword) {
                                JOptionPane.showMessageDialog(null,
                                        "Do not use space ( ), comma (,), or vertical bar (|)",
                                        "Sign up",
                                        JOptionPane.ERROR_MESSAGE);
                            } else if (length && digit && Uppercase && Lowercase) {
                                invalid = false;
                                created = true;
                                loggedIn = true;
                                JOptionPane.showMessageDialog(null, "Sign up successful",
                                        "Sign up complete", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Password has to be at least 8 characters including " +
                                                "Lowercase, Uppercase, and number",
                                        "Sign up",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } // end while
                    } // end while
                    break;
                }
                case ("closed"): {
                    return;
                }
            } // end switch
        } // login complete
        SwingUtilities.invokeLater(() -> {
            JFrame frame = null;
            try {
                frame = new Frame("Library Management System", jdbc, username, password);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            frame.setVisible(true);
        });
    }
}
