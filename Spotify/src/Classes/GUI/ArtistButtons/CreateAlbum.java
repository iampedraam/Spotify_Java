package Classes.GUI.ArtistButtons;

import Classes.Album;
import Classes.DataBase;
import Classes.GUI.jframes;
import Classes.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CreateAlbum implements ActionListener {

    jframes createAlbumPage = new jframes("New Album");
    JTextField albumNF = new JTextField();
    JTextField songNameF=new JTextField();
    JButton createAlbum = new JButton("Create Album");
    JButton addSong=new JButton("Add Song");
    List<String> songNames = new ArrayList<>();

    public CreateAlbum() {

        createAlbumPage.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(68, 184, 74));

        GridBagConstraints loc = new GridBagConstraints();
        loc.gridx = 0;
        loc.insets = new Insets(20, 20, 20, 20);

        Dimension size = new Dimension(120, 30);

        //Album Name
        JLabel alN = new JLabel("Album Name:");
        loc.gridy = 0;
        panel.add(alN, loc);
        albumNF.setPreferredSize(size);
        loc.gridx = 1;
        panel.add(albumNF, loc);

        //Add Song
        JLabel songNameL=new JLabel("Song Name:");
        loc.gridx=0;
        loc.gridy=1;
        panel.add(songNameL,loc);
        songNameF.setPreferredSize(size);
        loc.gridx = 1;
        panel.add(songNameF, loc);
        addSong.setFocusable(false);
        addSong.setSize(200, 200);
        addSong.addActionListener(this);
        loc.gridx=2;
        panel.add(addSong,loc);



        //submit button
        createAlbum.setFocusable(false);
        createAlbum.setSize(200, 200);
        createAlbum.addActionListener(this);
        loc.gridx=0;
        loc.gridy=2;
        panel.add(createAlbum,loc);

        //add panel to Frame
        createAlbumPage.add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        List<Song> songsList = new ArrayList<>();
        if (e.getSource() == addSong) {
            Song song = new Song();

            if (song.isSongSaved(songNameF.getText()) && song.isSongForArtist(songNameF.getText(), DataBase.getArtName(DataBase.getCurrentUserIndex()))) {
                songNames.add(songNameF.getText());
                JOptionPane.showMessageDialog(null, "Song Added to album", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Song Not Found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == createAlbum) {
            Album album = new Album();
            String albumName = albumNF.getText();
            String artistName = DataBase.getArtName(DataBase.getCurrentUserIndex());
            album.saveAlbumDetails(albumName, artistName, songNames); // Save
            JOptionPane.showMessageDialog(null, "Album Created Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            createAlbumPage.dispose();
        }
    }
}
