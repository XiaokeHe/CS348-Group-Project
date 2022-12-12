import java.awt.*;
import java.awt.event.*;
import java.sql.Statement;
import javax.swing.*;
import java.util.Objects;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;


class Borrow extends JFrame {
    static JButton add;
    static String book_id;
    static String cus_id;

    private static String action;

    static JTextArea bookIdField;
    static JTextArea cusIdField;
    Statement statement;
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == add) {
                action = "add";
                if (!bookIdField.getText().equals("") && !cusIdField.getText().equals("") ) {
                    book_id = bookIdField.getText();
                    cus_id = cusIdField.getText();
                    cusIdField.setText("");
                    bookIdField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter all information",
                            "Book Info Error", JOptionPane.ERROR_MESSAGE);
                    action = "no";
                }
                if (action != "no") {
                    try {
                        String sql = "INSERT INTO Loan_Record VALUES('"+ book_id + "', '"+ cus_id + "')";
                        int x = statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "Borrow Success!",
                                "Borrow Info", JOptionPane.ERROR_MESSAGE);
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
    public Borrow(String title) {
        super(title);
        setLayout(new BorderLayout());
        this.setSize(1000,200);
        Toolkit computer1 = Toolkit.getDefaultToolkit();
        Dimension dim = computer1.getScreenSize();
        int x = (dim.width/2) - (this.getWidth()/2);
        int y = (dim.height/2) - (this.getHeight()/2);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocation(x, y);
        this.setLocationRelativeTo(null);

        JPanel parent = new JPanel();

        add = new ButtonColor("Confirm borrow",new Dimension(120,50));
        add.addActionListener(actionListener);
        JPanel confirm_box = new JPanel();
        confirm_box.add(add);

        Box Cus_ID = Box.createHorizontalBox();
        JLabel cus_id = new JLabel("Customer ID:");
        cusIdField = new JTextArea();
        cusIdField.setColumns(10);
        cusIdField.setRows(1);
        Cus_ID.add(cus_id);
        Cus_ID.add(cusIdField);

        Box Book_ID = Box.createHorizontalBox();
        JLabel Bok_id = new JLabel("Book ID:");
        bookIdField = new JTextArea();
        bookIdField.setColumns(10);
        bookIdField.setRows(1);
        Book_ID.add(Bok_id);
        Book_ID.add(bookIdField);

        parent.add(Book_ID);
        parent.add(Cus_ID);
        parent.add(confirm_box);
        this.add(parent);
    }
}
