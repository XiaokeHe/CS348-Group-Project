import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.sql.SQLException;

public class Run {
    public static void main(String args[]) {
        String jdbc = "jdbc:mysql://localhost:3306/Library Managament";
        String username = "root";
        String password = "20020530hxk!";
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
