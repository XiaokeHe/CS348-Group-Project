import javax.swing.*;
import java.awt.*;
import java.sql.*;


public class CustomerUI extends JFrame{
    private final JTextField firstnameTextField;
    private final JTextField lastnameTextField;
    private final JTextField idTextField;
    private final JButton addButton;
    private final JButton deleteButton;
    private final JLabel firstnameLabel;
    private final JLabel lastnameLabel;
    private final JLabel idLabel;
    GridBagConstraints constraints;



    private CustomerUI() {
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        firstnameLabel = new JLabel("First Name:");
        firstnameTextField = new JTextField(10);

        constraints.gridx = 0;
        constraints.gridwidth = 2;
        add(firstnameLabel,constraints);

        constraints.gridx = 2;
        add(firstnameTextField,constraints);


        lastnameLabel = new JLabel("Last Name:");
        lastnameTextField = new JTextField(10);
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(lastnameLabel,constraints);
        constraints.gridx = 2;
        add(lastnameTextField,constraints);

        constraints.gridwidth = 1;
        constraints.gridx = 2;
        constraints.gridy = 3;
        addButton = new JButton("Add Customer");
        add(addButton,constraints);

        JSeparator s = new JSeparator();
        s.setOrientation(SwingConstants.HORIZONTAL);
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        add(s,constraints);

        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 5;
        idLabel = new JLabel("Customer ID:");
        add(idLabel,constraints);
        constraints.gridx = 2;
        idTextField = new JTextField(10);
        add(idTextField,constraints);

        constraints.gridy = 6;
        deleteButton = new JButton("Delete Customer");

        add(deleteButton,constraints);


        setTitle("Manage Customer");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    public static void manage(CustomerUI customerUI, Statement statement) {
        JButton addButton = customerUI.addButton;
        JButton deleteButton = customerUI.deleteButton;


        addButton.addActionListener((actionEvent) -> {
            String firstname = customerUI.firstnameTextField.getText();
            String lastname = customerUI.lastnameTextField.getText();
            if(firstname.equals("")||lastname.equals("")){
                JOptionPane.showMessageDialog(null, "Please enter valid first name and" +
                        " last name for customer", "Add Customer", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    String sql = "select customer_id from Customer order by customer_id desc limit 1";
                    ResultSet resultSet = statement.executeQuery (sql);
                    resultSet.next ();
                    String prevId = resultSet.getString("customer_id");
                    prevId = prevId.substring(1);
                    int prevNumber = Integer.parseInt(prevId);
                    int IdNumber = prevNumber + 1;
                    String customerId = "C"+ String.format("%08d", IdNumber);
                    String customerName = firstname+" "+lastname;
                    String sql2 = "INSERT INTO Customer VALUES('"+customerId+"','" + customerName + "')";
                    statement.execute(sql2);
                    customerUI.firstnameTextField.setText("");
                    customerUI.lastnameTextField.setText("");
                    String message = "The customer has been successfully added! His/Her id is: "+customerId;
                    JOptionPane.showMessageDialog(null, message, "successfully added"
                            , JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception e1) {
                    e1.printStackTrace () ;
                }

            }

        });

        deleteButton.addActionListener((actionEvent) -> {
            String id = customerUI.idTextField.getText();
            if(id.equals("")){
                JOptionPane.showMessageDialog(null, "Please enter valid customer's ID"
                        , "Delete Customer", JOptionPane.ERROR_MESSAGE);
            }else {
                try {
                    String getID = "SELECT count(*) as c FROM Customer WHERE customer_id='" + id + "'";
                    ResultSet r1 = statement.executeQuery(getID);
                    r1.next();
                    String res = r1.getString("c");
                    if(res == "0") {
                        JOptionPane.showMessageDialog(null, "The customer ID entered does not" +
                                " exist, please try again", "Delete Customer", JOptionPane.ERROR_MESSAGE);
                    } else {
                        String delete = "DELETE FROM Customer WHERE customer_id='" + id + "'";
                        statement.execute(delete);
                        customerUI.idTextField.setText("");
                        String message = "The customer has been successfully deleteed!";
                        JOptionPane.showMessageDialog(null, message, "successfully deleted"
                                , JOptionPane.INFORMATION_MESSAGE);
                    }

                }
                catch(Exception e2) {
                    e2.printStackTrace();
                }
            }

        });


    }

    public static void main(String[] args) throws SQLException {
        String jdbc ="jdbc:mysql://localhost:3306/348library";
        String username = "root";
        String password = "sophy100098";
        Connection connection = DriverManager.getConnection(jdbc, username, password);
        Statement statement = connection.createStatement();
        CustomerUI customerUI = new CustomerUI();
        manage(customerUI,statement);
        SwingUtilities.invokeLater(() -> {
            customerUI.setVisible(true);
        });

    }


}
