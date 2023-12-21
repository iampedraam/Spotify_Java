package Classes.GUI.ArtistButtons;

import Classes.DataBase;
import Classes.GUI.jframes;

import javax.swing.*;
import java.awt.*;

public class ArtistAccountInfo {

    jframes artInfo = new jframes("Account Detail");

    public ArtistAccountInfo() {

        //User Data set
        String username = DataBase.users[DataBase.getCurrentUserIndex()];
        String password = DataBase.passwords[DataBase.getCurrentUserIndex()];
        String Birthday = DataBase.birthdays[DataBase.getCurrentUserIndex()];
        String gender = DataBase.genders[DataBase.getCurrentUserIndex()];
        String accountType = DataBase.accTypes[DataBase.getCurrentUserIndex()];
        String artN = DataBase.artName[DataBase.getCurrentUserIndex()];

        artInfo.setSize(600, 600);
        artInfo.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(68, 184, 74));

        GridBagConstraints loc = new GridBagConstraints(); //Data panel
        loc.insets = new Insets(10, 40, 40, 80);
        loc.gridx = 0;

        //username
        JLabel userN = new JLabel("Username: " + username);
        loc.gridy = 0;
        panel.add(userN, loc);
        //pass
        JLabel pass = new JLabel("Password: " + password);
        loc.gridy = 1;
        panel.add(pass, loc);
        //gender
        JLabel genderL = new JLabel("Gender: " + gender);
        loc.gridx = 1;
        loc.gridy = 0;
        panel.add(genderL, loc);
        //account type
        JLabel accT = new JLabel("Account Type: " + accountType);
        loc.gridx = 0;
        loc.gridy = 2;
        panel.add(accT, loc);
        //birthday
        JLabel birth = new JLabel("Birthday: " + Birthday);
        loc.gridx = 1;
        loc.gridy = 1;
        panel.add(birth, loc);
        //Artistic Name
        JLabel artName = new JLabel("Artistic Name: " + artN);
        loc.gridy = 2;
        panel.add(artName, loc);

        artInfo.add(panel, BorderLayout.CENTER);
    }
}
