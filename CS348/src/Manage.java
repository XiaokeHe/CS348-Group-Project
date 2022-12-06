import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.util.Objects;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

class Manage extends JFrame {
    static BigInteger isbn;
    static String book_id;
    static String titles;
    static String author;
    static String genre;
    static float price;
    static JTextArea isbnField;
    static JTextArea bookIdField;
    static JTextArea authorField;
    static JTextArea titleField;
    static JTextArea genreField;
    static JTextArea priceField;
    static JTextArea locationField;
    static JButton add;
    static JButton delete;
    private static String action;
    Statement statement;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == add) { // incomplete
                action = "add";
                if (!isbnField.getText().equals("") && !titleField.getText().equals("") && !authorField.getText().equals("") && !genreField.getText().equals("")) {
                    isbn = new BigInteger(isbnField.getText());
                    BigInteger isbnMin = new BigInteger("1000000000000");
                    BigInteger isbnMax = new BigInteger("9999999999999");
                    if (isbn.compareTo(isbnMin) < 0 || isbn.compareTo(isbnMax) > 0) {
                        JOptionPane.showMessageDialog(null, "ISBN has to be 13 digits number",
                                "Book Info Error", JOptionPane.ERROR_MESSAGE);
                        action = "no";
                    }
                    titles = titleField.getText();
                    author = authorField.getText();
                    genre = genreField.getText();
                    isbnField.setText("");
                    titleField.setText("");
                    authorField.setText("");
                    genreField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter all information",
                            "Book Info Error", JOptionPane.ERROR_MESSAGE);
                    action = "no";
                }
                System.out.println(isbn);
                if (action != "no") {
                    try {
                        String sql = "select book_id from Book order by book_id desc limit 1";
                        ResultSet resultSet = statement.executeQuery (sql);
                        resultSet.next ();
                        String prevId = resultSet.getString("book_id");
                        prevId = prevId.substring(1);
                        int prevNumber = Integer.parseInt(prevId);
                        int IdNumber = prevNumber + 1;
                        String bookId = "B"+ String.format("%08d", IdNumber);
                        String sql2 = "INSERT INTO Book VALUES('"+bookId+"'," + isbn + ",'available', 'L-1-30-009')";
                        int x = statement.executeUpdate(sql2);

                        price = (float) 19.99;
                        String addBookInfo = "INSERT INTO Book_Info VALUES(" + isbn + ",'" + titles + "','" + author + "','"
                                + genre + "'," + price + ")";
                        statement.execute(addBookInfo);
                    } catch (Exception e1) {
                        e1.printStackTrace () ;
                    }
                }
            }
            if (e.getSource() == delete) {
                action = "delete";
                book_id = bookIdField.getText();
                try {
                    action = "delete";
                    String ISBN = "";
                    String getISBN = "SELECT ISBN FROM Book WHERE book_id='" + book_id + "'";
                    ResultSet r1 = statement.executeQuery(getISBN);
                    int temp = 0;
                    while (r1.next()) {
                        ISBN = r1.getString("ISBN");
                        System.out.println(ISBN);
                    }
                    String getCount = "SELECT COUNT(ISBN) FROM Book WHERE ISBN = '" + ISBN + "'";
                    ResultSet r2 = statement.executeQuery(getCount);
                    while(r2.next()) {
                        if (r2.getString("COUNT(ISBN)").equals("1")) {
                            temp = 1;
                        }
                    }
                    if (temp == 1) {
                        String deleteInfo = "DELETE FROM Book_Info WHERE ISBN='" + ISBN + "'";
                        statement.execute(deleteInfo);
                    }
                    String deleteBook = "DELETE FROM Book WHERE book_id='" + book_id + "'";
                    statement.execute(deleteBook);
                    bookIdField.setText("");
                }
                catch(Exception e2) {
                    e2.printStackTrace();
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

    public Manage(String title, Statement statement) {
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
        add = new ButtonColor("Add Book", new Dimension(120, 50));
        add.addActionListener(actionListener);
        delete = new ButtonColor("Delete Book", new Dimension(120, 50));
        delete.addActionListener(actionListener);

        JPanel parent = new JPanel();
        Box ip_box = Box.createHorizontalBox();
        Box port_box = Box.createHorizontalBox();
        Box file_box = Box.createHorizontalBox();
        Box Genre = Box.createHorizontalBox();
        Box Status = Box.createHorizontalBox();
        Box Book_id = Box.createHorizontalBox();
        Box Price = Box.createHorizontalBox();
        Box Location = Box.createHorizontalBox();


        JPanel receive_box = new JPanel();
        JPanel adjust = new JPanel();
        JLabel isbnLabel = new JLabel("Enter the ISBN:");
        isbnField = new JTextArea();
        isbnField.setColumns(10);
        isbnField.setRows(1);
        ip_box.add(isbnLabel);
        ip_box.add(isbnField);

        JLabel titleLabel = new JLabel("Enter the title:");
        titleField = new JTextArea();
        titleField.setColumns(10);
        titleField.setRows(1);
        file_box.add(titleLabel);
        file_box.add(titleField);

        JLabel authorLabel = new JLabel("Enter the author name:");
        authorField = new JTextArea();
        authorField.setColumns(10);
        authorField.setRows(1);
        port_box.add(authorLabel);
        port_box.add(authorField);

        JLabel genreLabel = new JLabel("Enter the genre:");
        genreField = new JTextArea();
        genreField.setColumns(10);
        genreField.setRows(1);
        Genre.add(genreLabel);
        Genre.add(genreField);

        JLabel bookIdLabel = new JLabel("Enter the book_id:");
        bookIdField = new JTextArea();
        bookIdField.setColumns(10);
        bookIdField.setRows(1);
        Book_id.add(bookIdLabel);
        Book_id.add(bookIdField);

        JTextArea received_view = new JTextArea();
        received_view.setSize(200, 300);
        received_view.setEditable(false);
        JScrollPane scroll_button = new JScrollPane(received_view, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        receive_box.add(add);
        receive_box.add(delete);
        adjust.setLayout(new FlowLayout(FlowLayout.LEFT, 70, 20));
        adjust.add(received_view);
        adjust.add(scroll_button);

        parent.add(ip_box);
        parent.add(port_box);
        parent.add(file_box);

        parent.add(Genre);
        parent.add(Status);
        parent.add(Book_id);
        parent.add(receive_box);
        parent.add(adjust);
        this.add(parent);
        this.statement = statement;

        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("icon.jpg")));
        this.setIconImage(logo.getImage());
    }

    public static String getAction() {
        return action;
    }

    public static BigInteger getIsbn() {
        return isbn;
    }

    public static String getTitles() {
        return titles;
    }

    public static String getAuthor() {
        return author;
    }

    public static String getGenre() {
        return genre;
    }
}