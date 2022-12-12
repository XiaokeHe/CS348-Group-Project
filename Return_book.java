import java.awt.*;
import javax.swing.*;

class Return_book extends JFrame{
    public Return_book(String title) {
        super(title);
        setLayout(new BorderLayout());
        this.setSize(200,200);
        Toolkit computer1 = Toolkit.getDefaultToolkit();
        Dimension dim = computer1.getScreenSize();
        int x = (dim.width/2) - (this.getWidth()/2);
        int y = (dim.height/2) - (this.getHeight()/2);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocation(x, y);
        this.setLocationRelativeTo(null);

        JPanel parent = new JPanel();

        JButton Return = new ButtonColor("Return Book",new Dimension(120,50));
        JPanel return_box = new JPanel();
        return_box.add(Return);

        Box BK_id = Box.createHorizontalBox();
        JLabel bk = new JLabel("Enter the Book_ID:");
        JTextArea Bk_id = new JTextArea();
        Bk_id.setColumns(10);
        Bk_id.setRows(1);
        BK_id.add(bk);
        BK_id.add(Bk_id);


        parent.add(BK_id);
        parent.add(return_box);
        this.add(parent);
    }
}