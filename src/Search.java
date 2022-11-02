import java.awt.*;
import javax.swing.*;

class Search extends JFrame {
    public Search(String title) {
        super(title);
        setLayout(new BorderLayout());
        this.setSize(1000, 200);
        Toolkit default1 = Toolkit.getDefaultToolkit();
        Dimension dim = default1.getScreenSize();
        int x = (dim.width / 2) - (this.getWidth() / 2);
        int y = (dim.height / 2) - (this.getHeight() / 2);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocation(x, y);
        this.setLocationRelativeTo(null);

        JButton search = new ButtonColor("Search", new Dimension(100, 30));
        JPanel parent = new JPanel();
        Box file_box = Box.createHorizontalBox();
        Box port_box = Box.createHorizontalBox();
        JPanel send_button = new JPanel();
        JPanel adjust = new JPanel();
        JLabel file_name = new JLabel("Search by:Book ID/ISBN/Title/Genre:");


        file_box.add(file_name);

        JLabel port_name = new JLabel("Enter the Book_ID/ISBN/Title/Genre:");
        JTextArea port = new JTextArea();
        JTextArea received_view = new JTextArea();
        received_view.setSize(200, 300);
        received_view.setEditable(false);
        port.setColumns(20);
        port.setRows(1);
        port_box.add(port_name);
        port_box.add(port);
        send_button.add(search);
        adjust.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
        adjust.add(received_view);
        parent.add(file_box);
        parent.add(port_box);
        parent.add(send_button);
        parent.add(adjust);
        this.add(parent);

        ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("icon.jpg"));
        this.setIconImage(logo.getImage());


    }
}