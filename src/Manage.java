import java.awt.*;
import javax.swing.*;

class Manage extends JFrame{
    public Manage(String title)
    {
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
        JButton add = new ButtonColor("Add Book",new Dimension(120,50));
        JButton delete = new ButtonColor("Delete Book",new Dimension(120,50));

        JPanel parent = new JPanel();
        Box ip_box = Box.createHorizontalBox();
        Box port_box = Box.createHorizontalBox();
        Box file_box = Box.createHorizontalBox();
        Box Genre = Box.createHorizontalBox();
        Box Status = Box.createHorizontalBox();
        Box Book_id = Box.createHorizontalBox();


        JPanel receive_box = new JPanel();
        JPanel adjust = new JPanel();
        JLabel ip_name = new JLabel("Enter the ISBN:");
        JTextArea ip = new JTextArea();
        ip.setColumns(10);
        ip.setRows(1);

        JLabel file_name = new JLabel("Enter the title:");
        JTextArea filename = new JTextArea();
        filename.setColumns(10);
        filename.setRows(1);
        ip_box.add(ip_name);
        ip_box.add(ip);

        JLabel author = new JLabel("Enter the author name:");
        JTextArea port = new JTextArea();

        JLabel genre = new JLabel("Enter the genre:");
        JTextArea ge = new JTextArea();
        ge.setColumns(10);
        ge.setRows(1);
        Genre.add(genre);
        Genre.add(ge);

        JLabel status = new JLabel("Enter the status:");
        JTextArea st = new JTextArea();
        st.setColumns(10);
        st.setRows(1);
        Status.add(status);
        Status.add(st);

        JLabel bk_id = new JLabel("Enter the book id:");
        JTextArea bk = new JTextArea();
        bk.setColumns(10);
        bk.setRows(1);
        Book_id.add(bk_id);
        Book_id.add(bk);

        JTextArea received_view = new JTextArea();
        received_view.setSize(200,300);
        received_view.setEditable(false);
        JScrollPane scroll_button = new JScrollPane(received_view,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        port.setColumns(10);
        port.setRows(1);
        port_box.add(author);
        port_box.add(port);
        receive_box.add(add);
        receive_box.add(delete);
        adjust.setLayout(new FlowLayout(FlowLayout.LEFT,70,20));
        adjust.add(received_view);
        adjust.add(scroll_button);
        file_box.add(file_name);
        file_box.add(filename);

        parent.add(ip_box);
        parent.add(port_box);
        parent.add(file_box);

        parent.add(Genre);
        parent.add(Status);
        parent.add(Book_id);
        parent.add(receive_box);
        parent.add(adjust);
        this.add(parent);

        ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("icon.jpg"));
        this.setIconImage(logo.getImage());




    }
}