package hospital.management.system;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {

    JTextField textField;
    JPasswordField passwordField;
    JButton b1, b2;

    Login() {
        setTitle("Hospital Management - Login");

        // Username Label
        JLabel nameLabel = new JLabel("Username");
        nameLabel.setBounds(40, 20, 100, 30);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(nameLabel);

        // Password Label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(40, 70, 100, 30);
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(passwordLabel);

        // Username Field
        textField = new JTextField();
        textField.setBounds(150, 20, 150, 30);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setBackground(new Color(255, 179, 0));
        add(textField);

        // Password Field
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 70, 150, 30);
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        passwordField.setBackground(new Color(255, 179, 0));
        add(passwordField);

        // Image
        JLabel label = new JLabel();
        try {
            java.net.URL imgURL = getClass().getClassLoader().getResource("icon/login1.png");
            if (imgURL != null) {
                ImageIcon imageIcon = new ImageIcon(imgURL);
                Image i1 = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(i1));
            } else {
                label.setText("Image not found");
            }
        } catch (Exception e) {
            label.setText("Error loading image");
        }
        label.setBounds(320, 30, 200, 200);
        add(label);


        // Buttons
        b1 = new JButton("Login");
        b1.setBounds(40, 140, 120, 30);
        b1.setFont(new Font("Serif", Font.BOLD, 15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(180, 140, 120, 30);
        b2.setFont(new Font("Serif", Font.BOLD, 15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        add(b2);

        // Frame Settings
        getContentPane().setBackground(new Color(100, 164, 170));
        setSize(750, 300);
        setLocation(400, 270);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                conn c = new conn(); // Your DB connection class
                String user = textField.getText();
                String pass = new String(passwordField.getPassword());

                // Safe SQL query using PreparedStatement
                String q = "SELECT * FROM login WHERE ID = ? AND pw = ?";
                PreparedStatement pst = c.connection.prepareStatement(q);
                pst.setString(1, user);
                pst.setString(2, pass);

                ResultSet resultSet = pst.executeQuery();

                if (resultSet.next()) {
                    new Reception(); // open next window
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
            }
        } else if (e.getSource() == b2) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
