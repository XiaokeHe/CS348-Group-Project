import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;

public class ShowResult extends JComponent implements Runnable {
    static JFrame resultFrame;
    private String action;
    //Information
    String filter;
    String bookInfo;
    ResultSet r;

    public ShowResult(String filter, String bookInfo, ResultSet r){
        this.filter = filter;
        this.bookInfo = bookInfo;
        this.r = r;
        action = null;
    }

    WindowListener windowListener = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            action = "close";
        }
    };

    @Override
    public void run() {
        //Design Frame
        resultFrame = new JFrame("Search Results");
        Container content = resultFrame.getContentPane();
        content.setLayout(new BorderLayout());
        resultFrame.setSize(850, 250);
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultFrame.addWindowListener(windowListener);

        // "Search by:<filter>:<bookInfo>(# of books)"
        JPanel panel1 = new JPanel();

        //Get books
        ArrayList<String[]> books = new ArrayList<>();
        String[] attributes = {"Book_ID", "ISBN", "Title", "Author", "Genre", "Price", "Location", "Status"};
        try {
            while(r.next()) {
                String[] b = new String[attributes.length];
                for(int i = 0; i < attributes.length; i++) {
                    b[i] = r.getString(attributes[i]);
                }
                books.add(b);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        String[][] data = new String[books.size()][attributes.length];
        for(int i = 0; i < books.size(); i++) {
            data[i] = books.get(i);
        }
        JTable table = new JTable(data, attributes);
        table.setVisible(true);
        table.getColumnModel().getColumn(1).setPreferredWidth(110);
        table.getColumnModel().getColumn(2).setPreferredWidth(140);
        table.getColumnModel().getColumn(3).setPreferredWidth(140);
        table.getColumnModel().getColumn(4).setPreferredWidth(170);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);
        table.getColumnModel().getColumn(6).setPreferredWidth(90);
        table.getColumnModel().getColumn(7).setPreferredWidth(70);
        String s = "Search by: " + filter + " : " + bookInfo + " (" + data.length + " book(s) are found)";
        JLabel summary = new JLabel(s);
        panel1.add(summary);
        JPanel panel2 = new JPanel();
        panel2.add(table.getTableHeader(), BorderLayout.NORTH);
        panel2.add(table);
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.PAGE_AXIS));
        panel3.add(panel1);
        panel3.add(panel2);
        content.add(panel3, BorderLayout.CENTER);
        resultFrame.setVisible(true);
        }
}
