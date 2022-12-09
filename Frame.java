import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;


public class Frame extends JFrame{
    int flag = -1;
    public Frame(String title)
    {
        super(title);
        setLayout(new BorderLayout());
        this.setSize(400,200);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        int x = (dim.width/2) - (this.getWidth()/2);
        int y = (dim.height/2) - (this.getHeight()/2);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocation(x, y);
        this.setLocationRelativeTo(null);

        JButton borrow = new ButtonColor("Borrow Book",new Dimension(120,50));
        JButton return_book = new ButtonColor("Return Book",new Dimension(120,50));
        JPanel parent = new JPanel();
        parent.setBackground(new Color(204,204,204));
        JPanel child1 = new JPanel();
        child1.setBackground(new Color(204,204,204));
        JPanel child2 = new JPanel();
        child2.setBackground(new Color(204,204,204));
        child1.setLayout(new FlowLayout(FlowLayout.CENTER));
        child1.add(borrow);
        child1.add(return_book);
        parent.add(child1);
        parent.add(child2);
        this.add(parent);

        ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("icon.jpg"));
        this.setIconImage(logo.getImage());

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
        return_book.addActionListener(e -> {
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
