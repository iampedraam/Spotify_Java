package Classes.GUI.ArtistButtons;

import Classes.Album;
import Classes.DataBase;
import Classes.GUI.jframes;
import Classes.Song;

import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArtName implements ActionListener {

    jframes artNamePage = new jframes("Set Artistic Name");
    JButton submitArtName = new JButton("Submit Artistic Name");
    JTextField newArtNameF = new JTextField();

    public ArtName() {

        artNamePage.setSize(500, 300);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(68, 184, 74));
        GridBagConstraints loc = new GridBagConstraints();
        loc.insets = new Insets(20, 20, 20, 20);

        //Current name
        JLabel currentArtName = new JLabel("Current Artistic Name: " + DataBase.artName[DataBase.getCurrentUserIndex()]);
        loc.gridx = 0;
        loc.gridy = 0;
        panel.add(currentArtName, loc);

        //New name label
        JLabel newArtNameL = new JLabel("New Artistic Name:");
        loc.gridy = 1;
        panel.add(newArtNameL, loc);


        //button
        submitArtName.addActionListener(this);
        submitArtName.setFocusable(false);
        submitArtName.setSize(200, 200);
        loc.gridy = 2;
        panel.add(submitArtName, loc);


        //new name field
        Dimension size = new Dimension(160, 30);
        newArtNameF.setPreferredSize(size);
        loc.gridy = 1;
        loc.gridx = 1;
        panel.add(newArtNameF, loc);


        //add panel to frame
        artNamePage.add(panel);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submitArtName) {
            String newArtisticName = newArtNameF.getText();
            int currentUserIndex = DataBase.getCurrentUserIndex();
            String oldArtisticName = DataBase.getArtName(currentUserIndex);

            // Update the artist name for its songs
            List<Song> songs = Song.getSongsByArtist(oldArtisticName);
            if (!songs.isEmpty()) {
                for (Song song : songs) {
                    song.updateSongArtist(song.getSongName(),newArtisticName);
                }
            }

            //Update the artist name for its albums
            List<Album> albums = Album.getAlbumsByArtist(oldArtisticName);
            if (!albums.isEmpty()) {
                for (Album album : albums) {
                    album.setArtist(newArtisticName);
                }
                Album.updateAlbumDetails(albums, oldArtisticName, newArtisticName);
            }


            DataBase.setArtName(currentUserIndex, newArtisticName);
            DataBase.saveData("data.txt");
            JOptionPane.showMessageDialog(null, "Artistic Name Set Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            artNamePage.dispose();
        }
    }
}
