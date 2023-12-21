package Classes.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage implements ActionListener {

    public static JButton login = new JButton("Login");
    public static JButton signup = new JButton("Signup");
    jframes firstPage = new jframes("Spotify");

    public WelcomePage() {
        firstPage.setLayout(new GridBagLayout());
        firstPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagConstraints loc = new GridBagConstraints();

        // Welcome text
        JLabel welcoming = new JLabel("Welcome to Spotify");
        welcoming.setFont(new Font("Arial", Font.BOLD, 22));
        welcoming.setForeground(new Color(255, 255, 255));
        loc.gridx = 0;
        loc.gridy = 0;
        loc.insets = new Insets(20, 0, 40, 0);
        loc.anchor = GridBagConstraints.CENTER;
        loc.gridwidth = 2;
        firstPage.add(welcoming, loc);

        // Login Text
        JLabel loginT = new JLabel("If you have already signed up, login:");
        loc.gridy = 1;
        loc.anchor = GridBagConstraints.CENTER;
        loc.gridwidth = 2;
        loc.insets = new Insets(20, 0, 10, 0);
        firstPage.add(loginT, loc);

        // Login Button
        login.setFocusable(false);
        login.addActionListener(this);
        loc.gridy = 2;
        loc.anchor = GridBagConstraints.CENTER;
        firstPage.add(login, loc);

        // Signup Text
        JLabel signupT = new JLabel("If you don't have an account, please signup first:");
        loc.gridy = 3;
        loc.anchor = GridBagConstraints.CENTER;
        loc.gridwidth = 2;
        firstPage.add(signupT, loc);

        // Signup Button
        signup.setFocusable(false);
        signup.addActionListener(this);
        loc.gridy = 4;
        loc.anchor = GridBagConstraints.CENTER;
        firstPage.add(signup, loc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            Login login = new Login();
        } else if (e.getSource() == signup) {
            Signup signup = new Signup();
        }

    }
}

