package Classes.GUI;

import Classes.GUI.ArtistButtons.*;
import Classes.GUI.ListenerButtons.ViewAllSongs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileArtist implements ActionListener {

    jframes artProf = new jframes("Spotify");
    JButton details = new JButton("Account Details");
    JButton setArtName = new JButton("Set Artistic name");
    JButton viewS = new JButton("Artist Songs");
    JButton viewAllS = new JButton("All Songs");
    JButton createSong = new JButton("Create Song");
    JButton removeSong = new JButton("Remove Song");
    JButton viewAlb = new JButton("View Albums");
    JButton createAlbum = new JButton("Create New Album");
    JButton removeA = new JButton("Remove Album");

    public ProfileArtist() {

        artProf.setSize(600, 600);

        artProf.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(68, 184, 74));


        GridBagConstraints loc = new GridBagConstraints(); //Playlist panel
        loc.insets = new Insets(10, 10, 30, 30);
        loc.gridx = 0;


        //Buttons
        //Account details
        details.addActionListener(this);
        details.setFocusable(false);
        details.setSize(200, 200);
        loc.gridy = 0;
        panel.add(details, loc);

        //Set Artistic name
        setArtName.addActionListener(this);
        setArtName.setFocusable(false);
        setArtName.setSize(200, 200);
        loc.gridy = 1;
        panel.add(setArtName, loc);

        //View Artist Songs
        viewS.addActionListener(this);
        viewS.setFocusable(false);
        viewS.setSize(200, 200);
        loc.gridy = 2;
        panel.add(viewS, loc);

        //Create Song
        createSong.addActionListener(this);
        createSong.setFocusable(false);
        createSong.setSize(200, 200);
        loc.gridy = 3;
        panel.add(createSong, loc);

        //Remove Song
        removeSong.addActionListener(this);
        removeSong.setFocusable(false);
        removeSong.setSize(200, 200);
        loc.gridy = 4;
        panel.add(removeSong, loc);

        //View Albums
        viewAlb.addActionListener(this);
        viewAlb.setSize(200, 200);
        viewAlb.setFocusable(false);
        loc.gridx = 1;
        loc.gridy = 0;
        panel.add(viewAlb, loc);

        //Create Album
        createAlbum.addActionListener(this);
        createAlbum.setFocusable(false);
        createAlbum.setSize(200, 200);
        loc.gridy = 1;
        panel.add(createAlbum, loc);


        //Remove Album
        removeA.addActionListener(this);
        removeA.setFocusable(false);
        removeA.setSize(200, 200);
        loc.gridy = 2;
        panel.add(removeA, loc);

        //View All Songs
        viewAllS.setFocusable(false);
        viewAllS.addActionListener(this);
        viewAllS.setSize(200, 200);
        loc.gridy = 3;
        panel.add(viewAllS, loc);



        //add panel to frame
        artProf.add(panel, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == details) {
            ArtistAccountInfo aD = new ArtistAccountInfo();
        }
        if (e.getSource().equals(setArtName)) {
            ArtName artNameSet = new ArtName();
        }
        if (e.getSource().equals(viewS)) {
            ViewSongs view = new ViewSongs();
        }
        if (e.getSource().equals(createSong)) {
            CreateSong newS = new CreateSong();
        }
        if (e.getSource().equals(removeSong)) {
            DeleteSong delete = new DeleteSong();
        }
        if (e.getSource().equals(viewAlb)) {
            ViewAlbums vA = new ViewAlbums();
        }
        if (e.getSource().equals(createAlbum)) {
            CreateAlbum cA = new CreateAlbum();
        }
        if (e.getSource().equals(removeA)) {
            RemoveAlbum rA=new RemoveAlbum();
        }
        if (e.getSource() == viewAllS) {
            ViewAllSongs allSongs = new ViewAllSongs();
        }
    }
}
