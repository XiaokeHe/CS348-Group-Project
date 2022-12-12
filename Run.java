import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Run {
    public static void main(String args[])
    {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new Frame("Share File!!");
            frame.setVisible(true);
        });


    }
}
