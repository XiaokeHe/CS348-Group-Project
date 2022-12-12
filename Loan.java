import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.util.Objects;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

class Loan extends JFrame {
    static BigInteger isbn;
    static JButton add;
    static String book_id;
    static String c_id;
    static String r_id;
    static String Isbn;
    private static String action;
    static JTextArea isbnField;
    static JTextArea bookIdField;
    static JTextArea RIDField;
    static JTextArea CIDField;
    Statement statement;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == add) {
                action = "add";
                if (!isbnField.getText().equals("") && !bookIdField.getText().equals("") && !RIDField.getText().equals("") && !CIDField.getText().equals("")) {
                    isbn = new BigInteger(isbnField.getText());
                    BigInteger isbnMin = new BigInteger("1000000000000");
                    BigInteger isbnMax = new BigInteger("9999999999999");
                    if (isbn.compareTo(isbnMin) < 0 || isbn.compareTo(isbnMax) > 0) {
                        JOptionPane.showMessageDialog(null, "ISBN has to be 13 digits number",
                                "Book Info Error", JOptionPane.ERROR_MESSAGE);
                        action = "no";
                    }
                    book_id = bookIdField.getText();
                    c_id = CIDField.getText();
                    r_id = RIDField.getText();
                    isbnField.setText("");
                    bookIdField.setText("");
                    CIDField.setText("");
                    RIDField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter all information",
                            "Book Info Error", JOptionPane.ERROR_MESSAGE);
                    action = "no";
                }
                System.out.println(isbn);
                if (action != "no") {
                    try {
                        String sql = "INSERT INTO Loan_Record VALUES(' " + r_id +", '"+ book_id + "','" + c_id + "','" + isbn + ")";
                        int x = statement.executeUpdate(sql);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    };

    WindowListener windowListener = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent evt) {
            action = "closed";
        }
    };


    public Loan(String title)
    {
        super(title);
        setLayout(new BorderLayout());
        this.setSize(1000, 200);
        Toolkit computer1 = Toolkit.getDefaultToolkit();
        Dimension dim = computer1.getScreenSize();
        int x = (dim.width / 2) - (this.getWidth() / 2);
        int y = (dim.height / 2) - (this.getHeight() / 2);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocation(x, y);
        this.setLocationRelativeTo(null);
        add = new ButtonColor("Return Book", new Dimension(120, 50));
        add.addActionListener(actionListener);


        JPanel parent = new JPanel();
        Box R_id = Box.createHorizontalBox();
        Box ISbn = Box.createHorizontalBox();
        Box C_id = Box.createHorizontalBox();
        Box Book_id = Box.createHorizontalBox();



        JPanel receive_box = new JPanel();
        JPanel adjust = new JPanel();

        JLabel isbnLabel = new JLabel("Enter the ISBN:");
        isbnField = new JTextArea();
        isbnField.setColumns(10);
        isbnField.setRows(1);
        ISbn.add(isbnLabel);
        ISbn.add(isbnField);

        JLabel book_id = new JLabel("Enter the book_id:");
        bookIdField = new JTextArea();
        bookIdField.setColumns(10);
        bookIdField.setRows(1);
        Book_id.add(book_id);
        Book_id.add(bookIdField);

        JLabel r_label = new JLabel("Enter the record_id:");
        RIDField = new JTextArea();
        RIDField.setColumns(10);
        RIDField.setRows(1);
        R_id.add(r_label);
        R_id.add(RIDField);

        JLabel C_label = new JLabel("Enter the customer_id:");
        CIDField = new JTextArea();
        CIDField.setColumns(10);
        CIDField.setRows(1);
        C_id.add(C_label);
        C_id.add(CIDField);


        JTextArea received_view = new JTextArea();
        received_view.setSize(200, 300);
        received_view.setEditable(false);
        JScrollPane scroll_button = new JScrollPane(received_view, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        receive_box.add(add);
        adjust.setLayout(new FlowLayout(FlowLayout.LEFT, 70, 20));
        adjust.add(received_view);
        adjust.add(scroll_button);

        parent.add(ISbn);
        parent.add(C_id);
        parent.add(Book_id);

        parent.add(R_id);
        parent.add(receive_box);
        parent.add(adjust);
        this.add(parent);
        this.statement = statement;
    }
}