import javax.swing.*;
import java.awt.*;
import java.sql.*;

class Loanpage extends JFrame {
    Statement statement;
    static Container content;
    static JTextField searchInfo;
    static JButton searchButton;
    static JComboBox<String> dropdown;
    private static String info;
    private static String action;
    private static String filter;

    public Loanpage(Statement statement) {
        super("Search Loan Record");
        this.statement = statement;
        content = this.getContentPane();
        content.setLayout(new BorderLayout());
        this.setSize(1000, 500);
        this.setMinimumSize(new Dimension(1000, 500));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        //Dropdown Menu
        JLabel select = new JLabel("Search by:");
        select.setVisible(true);
        String[] choices = {"Book ID", "ISBN", "Customer ID"};
        dropdown = new JComboBox<String>(choices);
        dropdown.setVisible(true);
        


        String[] time_choices = {"Past 30 days", "Past 10 days"};
        dropdown = new JComboBox<String>(time_choices);
        dropdown.setVisible(true);
        
        String[] status_choices = {"Return", "Borrow"};
        dropdown = new JComboBox<String>(status_choices);
        dropdown.setVisible(true);

        JPanel panel1 = new JPanel();
        panel1.add(select);
        panel1.add(dropdown);
        
        //Search Information
        JLabel labelInfo = new JLabel("Enter Book ID/ISBN/customer ID");
        searchInfo = new JTextField(15);

        searchButton = new JButton("Search");

        JPanel panel2 = new JPanel();
        panel2.add(labelInfo);
        panel2.add(searchInfo);
        panel2.add(searchButton);
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.PAGE_AXIS));
        panel3.add(panel1);
        panel3.add(panel2);
        content.add(panel3, BorderLayout.NORTH);
    }
}
