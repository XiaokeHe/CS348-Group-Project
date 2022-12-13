import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class Loanpage extends JFrame {
    Statement statement;
    static Container content;
    static JTextField searchInfo;
    static JTextArea from_date;
    static JTextArea to_date;
    static JButton searchButton;
    static JComboBox<String> dropdown;
    private static String info;
    private static String action;
    private static String filter;

    private static String start_date;
    private static String end_date;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == searchButton) {
                filter = dropdown.getSelectedItem().toString();
                if (searchInfo.getText().equals("") || from_date.getText().equals("") || to_date.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter all the information!",
                            "Missing Information", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    start_date = from_date.getText();
                    end_date = to_date.getText();
                    info = searchInfo.getText();
                    from_date.setText("");
                    to_date.setText("");
                    searchInfo.setText("");
                    ResultSet r; // Result is here!!!!!!!!!!!
                    if (filter.equals("Book ID")) {
                        if ((!info.substring(0,1).equals("B")) && (!info.substring(0, 1).equals("b"))) {
                            info = "B" + info;
                        }
                        if (info.substring(0,1).equals("b")) {  // sometimes users will use lowercase
                            info = "B" + info.substring(1);
                        }
                        String sql1 = "CALL searchLoan('" + start_date + "', '" + end_date + "')";
                        String sql2 = "SELECT * FROM result WHERE book_id = '" + info + "' ORDER BY borrow_date";
                        try {
                            statement.execute(sql1);
                            r = statement.executeQuery(sql2);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    else if (filter.equals("Customer ID")) {
                        if ((!info.substring(0,1).equals("C")) && (!info.substring(0, 1).equals("c"))) {
                            info = "C" + info;
                        }
                        if (info.substring(0,1).equals("c")) {  // sometimes users will use lowercase
                            info = "C" + info.substring(1);
                        }
                        String sql1 = "CALL searchLoan('" + start_date + "', '" + end_date + "')";
                        String sql2 = "SELECT * FROM result WHERE customer_id = '" + info + "' ORDER BY borrow_date";
                        try {
                            statement.execute(sql1);
                            r = statement.executeQuery(sql2);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        }
    };

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
        String[] choices = {"Book ID", "Customer ID"};
        dropdown = new JComboBox<String>(choices);
        dropdown.setVisible(true);
        JPanel panel1 = new JPanel();
        panel1.add(select);
        panel1.add(dropdown);

        JLabel from = new JLabel("From:");
        from.setVisible(true);
        from_date = new JTextArea();
        from_date.setColumns(10);
        from_date.setRows(1);
        JPanel panel5 = new JPanel();
        panel5.add(from);
        panel5.add(from_date);

        JLabel to = new JLabel("To:");
        to.setVisible(true);
        JPanel panel6 = new JPanel();
        to_date = new JTextArea();
        to_date.setColumns(10);
        to_date.setRows(1);
        panel6.add(to);
        panel6.add(to_date);


        //Search Information
        JLabel labelInfo = new JLabel("Enter Book Information: ");
        searchInfo = new JTextField(15);

        searchButton = new JButton("Search");
        searchButton.addActionListener(actionListener);


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
        content.add(panel3,BorderLayout.NORTH);
        content.add(panel8);
    }
}

