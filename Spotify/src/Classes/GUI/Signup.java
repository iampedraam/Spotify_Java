package Classes.GUI;

import Classes.DataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup implements ActionListener {

    String pass, user, d, m, y, g, at;
    jframes signupPage = new jframes("SignUp");
    JButton submitS = new JButton("Signup");
    JTextField userField = new JTextField();
    JTextField passField = new JTextField();
    JTextField day = new JTextField();
    JTextField year = new JTextField();
    String[] genderTypes = {"Male", "Female"}, accountTypes = {"Artist", "Listener"};
    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    JComboBox month = new JComboBox(months);
    JComboBox genderT = new JComboBox(genderTypes);
    JComboBox accT = new JComboBox(accountTypes);

    Signup() {

        Dimension size = new Dimension(150, 30);
        signupPage.setLayout(new BorderLayout());
        signupPage.setSize(500, 700);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(68, 134, 74));

        GridBagConstraints loc = new GridBagConstraints();
        loc.insets = new Insets(10, 10, 10, 10);
        loc.gridx = 0;

        //User
        JLabel username = new JLabel("Username:");
        loc.gridy = 0;
        panel.add(username, loc);
        userField.setPreferredSize(size);
        loc.gridy = 1;
        panel.add(userField, loc);

        //Pass
        JLabel password = new JLabel("Password:");
        loc.gridy = 2;
        panel.add(password, loc);
        passField.setPreferredSize(size);
        loc.gridy = 3;
        panel.add(passField, loc);

        //Gender
        JLabel gender = new JLabel("Gender:");
        loc.gridy = 4;
        panel.add(gender, loc);
        loc.gridy = 5;
        panel.add(genderT, loc);

        //Birth
        JLabel birth = new JLabel("Date of Birth:");
        loc.gridy = 6;
        panel.add(birth, loc);
        JLabel dayL = new JLabel("Day:");
        loc.gridy = 7;
        panel.add(dayL, loc);
        day.setPreferredSize(size);
        loc.gridy = 8;
        panel.add(day, loc);
        loc.gridy = 9;
        panel.add(month, loc);
        JLabel yearL = new JLabel("Year:");
        loc.gridy = 10;
        panel.add(yearL, loc);
        year.setPreferredSize(size);
        loc.gridy = 11;
        panel.add(year, loc);

        //type
        JLabel type = new JLabel("Account Type:");
        loc.gridy = 12;
        panel.add(type, loc);
        loc.gridy = 13;
        panel.add(accT, loc);

        //Button
        submitS.addActionListener(this);
        submitS.setFocusable(false);
        submitS.setSize(300, 200);
        loc.insets = new Insets(30, 0, 0, 0);
        loc.gridy = 14;
        loc.gridwidth = 2;
        panel.add(submitS, loc);


        //add panel to frame
        signupPage.add(panel, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitS) {
            user = userField.getText();
            pass = passField.getText();
            d = day.getText();
            m = month.getSelectedItem().toString();
            y = year.getText();
            g = genderT.getSelectedItem().toString();
            at = accT.getSelectedItem().toString();

            if (pass.isEmpty() || user.isEmpty() || d.isEmpty() || y.isEmpty()) {
                if (passField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Password is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                if (userField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Username is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                if (day.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please Enter Day of birth", "Error", JOptionPane.ERROR_MESSAGE);
                }
                if (year.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please Enter Year of birth", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else { //all fields are filled
                //Store Data
                int index = DataBase.getNumUsers();
                DataBase.setUser(index, user);
                DataBase.setPass(index, pass);
                DataBase.setBirthdays(index, d, m, y);
                DataBase.setGender(index, g);
                DataBase.setAccTypes(index, at);
                DataBase.increaseUsers();
                DataBase.saveData("data.txt");
                //Pop-up Success
                JOptionPane.showMessageDialog(null, "Account created successfully!", "Signup completed", JOptionPane.INFORMATION_MESSAGE);
                signupPage.dispose();
            }
        }
    }
}
