import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.sql.*;


public class Frame extends JFrame{
    int flag = -1;
    public Frame(String title, String jdbc, String username, String password) throws Exception {
        super(title);
        setLayout(new BorderLayout());
        this.setSize(500,500);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        int x = (dim.width/2) - (this.getWidth()/2);
        int y = (dim.height/2) - (this.getHeight()/2);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocation(x, y);
        this.setLocationRelativeTo(null);

        JPanel parent = new JPanel(new FlowLayout(FlowLayout.LEFT));
        parent.setBackground(new Color(204,204,204));

        JPanel child0 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        child0.setSize(200, 100);
        child0.setBackground(new Color(204,204,204));
        JLabel addDelete = new JLabel("Add/Delete information");
        addDelete.setSize(new Dimension(200, 100));
        Font f = new Font("TimesRoman",Font.PLAIN,20);
        addDelete.setFont(f);
        addDelete.setBounds(80,20,250,80);
        addDelete.setBackground(new Color(204,204,204));
        child0.add(addDelete);

        JPanel child00 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        child00.setBackground(new Color(204,204,204));
        JButton book = new ButtonColor("Manage Book",new Dimension(180,50));
        JButton customer = new ButtonColor("Manage Customer",new Dimension(180,50));
        child00.add(book);
        child00.add(customer);

        JPanel child1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        child1.setSize(200, 100);
        child1.setBackground(new Color(204,204,204));
        JLabel borrowReturn = new JLabel("Book Transactions");
        borrowReturn.setSize(new Dimension(200, 100));
        Font f1 = new Font("TimesRoman",Font.PLAIN,20);
        borrowReturn.setFont(f1);
        borrowReturn.setBounds(80,20,250,80);
        borrowReturn.setBackground(new Color(204,204,204));
        child1.add(borrowReturn);


        JPanel child11 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        child11.setBackground(new Color(204,204,204));
        JButton borrow = new ButtonColor("Borrow Book",new Dimension(180,50));
        JButton returnBook = new ButtonColor("Return Book",new Dimension(180,50));
        child11.add(borrow);
        child11.add(returnBook);

        JPanel child2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        child2.setSize(200, 100);
        child2.setBackground(new Color(204,204,204));
        JLabel search = new JLabel("Search information");
        search.setSize(new Dimension(200, 100));
        Font f2 = new Font("TimesRoman",Font.PLAIN,20);
        search.setFont(f2);
        search.setBounds(80,20,250,80);
        search.setBackground(new Color(204,204,204));
        child2.add(search);

        JPanel child22 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        child22.setBackground(new Color(204,204,204));
        JButton searchBook = new ButtonColor("Search Book",new Dimension(180,50));
        JButton showRecord = new ButtonColor("Show Record",new Dimension(180,50));
        child22.add(searchBook);
        child22.add(showRecord);

        parent.add(child0);
        parent.add(child00);
        parent.add(child1);
        parent.add(child11);
        parent.add(child2);
        parent.add(child22);
        this.add(parent);

        ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("icon.jpg"));
        this.setIconImage(logo.getImage());

        Connection connection = DriverManager.getConnection(jdbc, username, password);
        Statement statement = connection.createStatement();

        customer.addActionListener(e -> {
            if(Frame.this.flag==-1){
                CustomerUI customerUI = new CustomerUI();
                Manage manage = new Manage(customerUI,statement);
                SwingUtilities.invokeLater(() -> {
                    customerUI.setVisible(true);
                });
                Borrow fs = new Borrow("Borrow");
                fs.setVisible(true);
                fs.setSize(430,430);
                Frame.this.flag=1;
                fs.addWindowListener(new WindowAdapter(){
                    public void windowClosed(WindowEvent e) {
                        Frame.this.flag=-1;
                    }
                });
            }
        });


        borrow.addActionListener(e -> {
            if(Frame.this.flag==-1){
                Borrow fs = new Borrow("Borrow");
                fs.setVisible(true);
                fs.setSize(430,430);
                Frame.this.flag=1;
                fs.addWindowListener(new WindowAdapter(){
                    public void windowClosed(WindowEvent e) {
                        Frame.this.flag=-1;
                    }
                });
            }
        });
        returnBook.addActionListener(e -> {
            if(Frame.this.flag==-1){
                Return_book fs = new Return_book("Return");
                fs.setVisible(true);
                fs.setSize(300,300);
                Frame.this.flag=1;
                fs.addWindowListener(new WindowAdapter(){
                    public void windowClosed(WindowEvent e) {
                        Frame.this.flag=-1;
                    }
                });
            }
        });
    }
}