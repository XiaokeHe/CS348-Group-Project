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

    public Loanpage() {
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
        String[] choices = {"Book ID", "ISBN", "Customer ID", "Title", "Author", "Genre"};
        dropdown = new JComboBox<String>(choices);
        dropdown.setVisible(true);
        JPanel panel1 = new JPanel();
        panel1.add(select);
        panel1.add(dropdown);

        JLabel from = new JLabel("From:");
        from.setVisible(true);
        JTextArea from_date = new JTextArea();
        from_date.setColumns(10);
        from_date.setRows(1);
        JPanel panel5 = new JPanel();
        panel5.add(from);
        panel5.add(from_date);

        JLabel to = new JLabel("To:");
        to.setVisible(true);
        JPanel panel6 = new JPanel();
        JTextArea to_date = new JTextArea();
        to_date.setColumns(10);
        to_date.setRows(1);
        panel6.add(to);
        panel6.add(to_date);

        JLabel by = new JLabel("By:");
        String[] status_choices = {"Return date", "Borrow date"};
        dropdown = new JComboBox<String>(status_choices);
        dropdown.setVisible(true);
        JPanel panel7 = new JPanel();
        panel7.add(by);
        panel7.add(dropdown);



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
        panel3.add(panel2);

        panel3.add(panel1);
        JPanel panel8 = new JPanel();
        panel8.add(panel5,BorderLayout.EAST);
        panel8.add(panel6,BorderLayout.WEST);
        panel8.add(panel7,BorderLayout.LINE_END);
        content.add(panel3,BorderLayout.NORTH);
        content.add(panel8);
    }
}
