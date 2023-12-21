package Classes.GUI;

import Classes.DataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener {

    jframes loginPage = new jframes("Login");
    JButton submitL = new JButton("Login");
    JTextField usernameField = new JTextField();
    JTextField passwordField = new JTextField();
    String[] types = {"Artist", "Listener"};
    JComboBox type = new JComboBox(types);

    Login() {

        DataBase.loadData("data.txt");


        Dimension size = new Dimension(150, 30);
        loginPage.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints loc = new GridBagConstraints();

        loc.insets = new Insets(20, 0, 0, 10);

        // Username Label and Field
        JLabel usernameLabel = new JLabel("User Name:");
        loc.gridx = 0;
        loc.gridy = 0;
        panel.add(usernameLabel, loc);
        usernameField.setPreferredSize(size);
        loc.gridx = 1;
        loc.gridy = 0;
        panel.add(usernameField, loc);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password:");
        loc.gridx = 0;
        loc.gridy = 1;
        panel.add(passwordLabel, loc);
        passwordField.setPreferredSize(size);
        loc.gridx = 1;
        loc.gridy = 1;
        panel.add(passwordField, loc);

        //Type Combo Box
        loc.gridx = 1;
        loc.gridy = 2;
        panel.add(type, loc);

        // Login Button
        submitL.setFocusable(false);
        loc.gridx = 0;
        loc.gridy = 3;
        loc.gridwidth = 3;
        panel.add(submitL, loc);

        //Add panel to frame
        panel.setBackground(new Color(68, 134, 74));
        loginPage.add(panel, BorderLayout.CENTER);

        //Button action listener
        submitL.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitL) {
            String user, pass, accType;
            user = usernameField.getText();
            pass = passwordField.getText();
            accType = type.getSelectedItem().toString();
            if (user.isEmpty() || pass.isEmpty()) { //User or Pass empty
                if (user.isEmpty() && pass.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Username and Password are empty!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (user.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Username is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (pass.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Password is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else { //User and Pass are filled
                int i;
                boolean userFound = false;
                for (i = 0; i < (DataBase.getNumUsers() + 1); i++) {
                    if (user.equals(DataBase.users[i]) && pass.equals(DataBase.passwords[i])) { //login success

                        DataBase.setCurrentUserIndex(i); //Set User Index Number to match data with each user

                        if (accType.equals(DataBase.accTypes[DataBase.getCurrentUserIndex()])) {
                            userFound = true;
                            JOptionPane.showMessageDialog(null, "You have successfully Signed in", "Login Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Wrong User Type!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        if (accType.equals("Listener") && userFound) { //Listener main page
                            ProfileListener pL = new ProfileListener();
                            loginPage.dispose();
                        } else if (accType.equals("Artist") && userFound) {
                            ProfileArtist pA = new ProfileArtist();
                            loginPage.dispose();
                        }
                        break;
                    }
                }
                if (!userFound) {
                    JOptionPane.showMessageDialog(null, "Account not found!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
