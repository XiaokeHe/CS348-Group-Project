import java.awt.*;
import javax.swing.*;

class Borrow extends JFrame {
    public Borrow(String title) {
        super(title);
        setLayout(new BorderLayout());
        this.setSize(100,100);
        Toolkit computer1 = Toolkit.getDefaultToolkit();
        Dimension dim = computer1.getScreenSize();
        int x = (dim.width/2) - (this.getWidth()/2);
        int y = (dim.height/2) - (this.getHeight()/2);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocation(x, y);
        this.setLocationRelativeTo(null);

        JPanel parent = new JPanel();

        JButton confirm = new ButtonColor("Confirm borrow",new Dimension(120,50));
        JPanel confirm_box = new JPanel();
        confirm_box.add(confirm);

        Box Cus_ID = Box.createHorizontalBox();
        JLabel cus_id = new JLabel("Customer ID:");
        JTextArea cus = new JTextArea();
        cus.setColumns(10);
        cus.setRows(1);
        Cus_ID.add(cus_id);
        Cus_ID.add(cus);

        Box Book_ID = Box.createHorizontalBox();
        JLabel Bok_id = new JLabel("Book ID:");
        JTextArea Bok = new JTextArea();
        Bok.setColumns(10);
        Bok.setRows(1);
        Book_ID.add(Bok_id);
        Book_ID.add(Bok);

        parent.add(Book_ID);
        parent.add(Cus_ID);
        parent.add(confirm_box);
        this.add(parent);
    }
}