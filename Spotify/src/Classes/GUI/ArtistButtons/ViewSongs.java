package Classes.GUI.ArtistButtons;

import Classes.DataBase;
import Classes.GUI.MusicPlayer;
import Classes.GUI.jframes;
import Classes.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewSongs {
    jframes viewSongsPage = new jframes("Songs");

    public ViewSongs() {

        viewSongsPage.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(68, 184, 74));

        GridBagConstraints loc = new GridBagConstraints();
        loc.gridx = 0;
        loc.gridy = 0;
        loc.insets = new Insets(20, 20, 20, 20);


        //retrieve songs of the Artist
        List<Song> songs = Song.getSongsByArtist(DataBase.getArtName(DataBase.getCurrentUserIndex()));


        //create button for each song
        for (Song song : songs) {
            JButton songButton = new JButton(song.getSongName()); // Button with song name
            songButton.addActionListener(new SongButtonListener(song));
            songButton.setFocusable(false);
            panel.add(songButton, loc);
            loc.gridy++;
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        viewSongsPage.add(scrollPane, BorderLayout.CENTER);

        //add panel to frame
        viewSongsPage.add(panel, BorderLayout.CENTER);

    }


    //when button is clicked
    private class SongButtonListener implements ActionListener {
        private final Song song;

        public SongButtonListener(Song song) {
            this.song = song;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            MusicPlayer musicPlayer = new MusicPlayer(song.getSongName(), song.getDuration(), song.getArtist());

        }
    }
}
