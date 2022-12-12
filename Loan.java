import javax.swing.*;
import java.awt.*;

    class Loan extends JFrame {
        public Loan(String title)
        {
            super(title);
            setLayout(new BorderLayout());
            this.setSize(500,200);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setLocation(50, 50);
            this.setLocationRelativeTo(null);

            JPanel parent = new JPanel();

            JButton Search = new ButtonColor("Search",new Dimension(100,50));
            JPanel return_box = new JPanel();
            return_box.add(Search);

            Box Enter = Box.createHorizontalBox();
            JLabel bk = new JLabel("Enter Book ID/ISBN/Record ID/customer ID:");
            JTextArea Bk_id = new JTextArea();
            Bk_id.setColumns(10);
            Bk_id.setRows(1);
            Enter.add(bk);
            Enter.add(Bk_id);

            parent.add(Enter);
            parent.add(return_box);
            this.add(parent);
        }
    }

