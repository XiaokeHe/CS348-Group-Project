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

        private static String action;

        static JTextArea bookIdField;

        Statement statement;

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == add) {
                    action = "add";
                    if (!bookIdField.getText().equals("") ) {
                        book_id = bookIdField.getText();
                        bookIdField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter all information",
                                "Book Info Error", JOptionPane.ERROR_MESSAGE);
                        action = "no";
                    }
                    if (action != "no") {
                        try {
                            String sql = "INSERT INTO Loan_Record VALUES('"+ book_id + "')";
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
            this.setSize(500, 200);
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

            Box Book_id = Box.createHorizontalBox();



            JPanel receive_box = new JPanel();
            JPanel adjust = new JPanel();



            JLabel book_id = new JLabel("Enter the book_id:");
            bookIdField = new JTextArea();
            bookIdField.setColumns(10);
            bookIdField.setRows(1);
            Book_id.add(book_id);
            Book_id.add(bookIdField);




            JTextArea received_view = new JTextArea();
            received_view.setSize(200, 300);
            received_view.setEditable(false);
            JScrollPane scroll_button = new JScrollPane(received_view, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            receive_box.add(add);
            adjust.setLayout(new FlowLayout(FlowLayout.LEFT, 70, 20));
            adjust.add(received_view);
            adjust.add(scroll_button);


            parent.add(Book_id);


            parent.add(receive_box);
            parent.add(adjust);
            this.add(parent);
            this.statement = statement;
        }
    }
