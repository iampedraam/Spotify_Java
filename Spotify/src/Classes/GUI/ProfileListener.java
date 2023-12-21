package Classes.GUI;

import Classes.GUI.ArtistButtons.ViewAlbums;
import Classes.GUI.ListenerButtons.ListenerAccountInfo;
import Classes.GUI.ListenerButtons.ViewAllSongs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileListener implements ActionListener {
    jframes listenProf = new jframes("Spotify");
    JButton accountInfo = new JButton("Account Info");
    JButton viewAllSongs = new JButton("Songs");
    JButton viewAlbums = new JButton("Albums");

    public ProfileListener() {


        listenProf.setSize(600, 600);
        listenProf.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(68, 184, 74));

        GridBagConstraints loc = new GridBagConstraints(); //Playlist panel
        loc.insets = new Insets(10, 10, 30, 30);
        loc.gridx = 0;


        //Buttons
        //Account Data
        accountInfo.addActionListener(this);
        accountInfo.setFocusable(false);
        accountInfo.setSize(200, 200);
        loc.gridy = 0;
        panel.add(accountInfo, loc);

        //Songs
        viewAllSongs.addActionListener(this);
        viewAllSongs.setFocusable(false);
        viewAllSongs.setSize(200, 200);
        loc.gridy = 1;
        panel.add(viewAllSongs, loc);


        //All Albums
        viewAlbums.addActionListener(this);
        viewAlbums.setFocusable(false);
        viewAlbums.setSize(200, 200);
        loc.gridx = 1;
        loc.gridy = 0;
        panel.add(viewAlbums, loc);


        //add panel to frame
        listenProf.add(panel, BorderLayout.CENTER);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == accountInfo) {
            ListenerAccountInfo lI = new ListenerAccountInfo();
        }
        if (e.getSource() == viewAllSongs) {
            ViewAllSongs songs = new ViewAllSongs();
        }
        if (e.getSource() == viewAlbums) {
            ViewAlbums vA = new ViewAlbums();
        }
    }
}
