import javax.swing.*;
import javax.swing.SwingUtilities;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class Run {
    public static void main(String args[]) {
        String jdbc = "jdbc:mysql://localhost:3306/cs348project";
        String username = "root";
        String password = "vnfjriCMDKEO1234";
        boolean loggedIn = false;



        SwingUtilities.invokeLater(() -> {
            JFrame frame = null;
            try {
                frame = new Frame("Library Management System", jdbc, username, password);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            frame.setVisible(true);
        });


        while (!loggedIn) {
            LoginGUI login = new LoginGUI();
            SwingUtilities.invokeLater(login);
            while (login.getAction() == null) {
                Thread.onSpinWait();
            } // end while
            switch (login.getAction()) {
                case ("login"): {
                    String id = login.getUsername();
                    String pw = login.getPassword();
                    try {
                        Connection connection = DriverManager.getConnection(jdbc, username, password);
                        Statement statement = connection.createStatement();
                        String sql = "select password from Employee where employee_id = '" + id + "'";
                        ResultSet resultSet = statement.executeQuery (sql);
                        resultSet.next ();
                        String sql_pw = resultSet.getString("password");
                        if (pw.equals(sql_pw)) {
                            loggedIn = true;
                        }
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "Entered ID doesn't exist",
                                "Message Login", JOptionPane.ERROR_MESSAGE);
                    }

                    if (loggedIn) {
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
                        String name = null;
                        String pw = null;
                        while (name == null || name.equals("")) {
                            name = JOptionPane.showInputDialog(null,
                                    "Enter Name",
                                    "Sign up", JOptionPane.QUESTION_MESSAGE);
                            if (name == null) {
                                created = true;
                                break;
                            }
                        }
                        boolean invalid = true;
                        while (name != null && (pw == null || pw.equals("") ||
                                invalid)) {
                            pw = JOptionPane.showInputDialog(null,
                                    "Enter password", "Sign up",
                                    JOptionPane.QUESTION_MESSAGE);
                            if (pw == null) {
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
                                try {
                                    Connection connection = DriverManager.getConnection(jdbc, username, password);
                                    Statement statement = connection.createStatement();
                                    String sql = "select employee_id from Employee order by employee_id desc limit 1";
                                    ResultSet resultSet = statement.executeQuery (sql);
                                    resultSet.next ();
                                    String prevId = resultSet.getString("employee_id");
                                    prevId = prevId.substring(1);
                                    int prevNumber = Integer.parseInt(prevId);
                                    int IdNumber = prevNumber + 1;
                                    String employee_id = "E"+ String.format("%03d", IdNumber);
                                    String sql2 = "INSERT INTO Employee VALUES('"+employee_id+"','" + name + "','" + pw + "')";
                                    statement.execute(sql2);
                                    JOptionPane.showMessageDialog(null, "Sign up successful, Your ID is: " + employee_id,
                                            "Sign up complete", JOptionPane.INFORMATION_MESSAGE);

                                } catch (Exception e1) {
                                    e1.printStackTrace () ;
                                }


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