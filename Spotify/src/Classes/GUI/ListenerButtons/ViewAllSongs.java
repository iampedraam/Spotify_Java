package Classes.GUI.ListenerButtons;

import Classes.GUI.MusicPlayer;
import Classes.GUI.jframes;
import Classes.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewAllSongs implements ActionListener {
    JTextField songNF = new JTextField();
    JButton find = new JButton("Search");

    public ViewAllSongs() {
        jframes viewAllSongsPage = new jframes("All Songs");
        viewAllSongsPage.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(68, 184, 74));

        GridBagConstraints loc = new GridBagConstraints();
        loc.gridx = 0;
        loc.insets = new Insets(20, 20, 20, 20);


        //retrieve songs
        List<Song> songs = Song.loadSongsFromFile();


        //Search Song
        Dimension size = new Dimension(120, 30);
        JLabel songName = new JLabel("Song Name:");
        loc.gridy = 0;
        panel.add(songName, loc);
        songNF.setPreferredSize(size);
        loc.gridx = 1;
        panel.add(songNF, loc);

        //Search button
        find.setFocusable(false);
        find.addActionListener(this);
        find.setSize(200, 200);
        loc.gridx = 1;
        loc.gridy = 1;
        panel.add(find, loc);


        //create button for each song
        loc.gridx = 0;
        loc.gridy = 2;
        JPanel songButtonsPanel = new JPanel(new GridBagLayout());
        songButtonsPanel.setBackground(new Color(68, 120, 74));
        for (Song song : songs) {
            JButton songButton = new JButton(song.getSongName() + " By " + song.getArtist()); // Button with song name
            songButton.addActionListener(new ViewAllSongs.SongButtonListener(song));
            songButton.setFocusable(false);
            songButtonsPanel.add(songButton, loc);
            loc.gridy++;
        }


        //ScrollPane
        JScrollPane scrollPane = new JScrollPane(songButtonsPanel);
        viewAllSongsPage.add(scrollPane, BorderLayout.CENTER);

        //add panel to frame
        viewAllSongsPage.add(panel, BorderLayout.NORTH);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == find) {
            Song song = new Song();

            if (!song.isSongSaved(songNF.getText())) {
                JOptionPane.showMessageDialog(null, "Song not found!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Song found = song.getSongFromName(songNF.getText());
                MusicPlayer music = new MusicPlayer(found.getSongName(), found.getDuration(), found.getArtist());
            }
        }
    }


    //All songs button
    private class SongButtonListener implements ActionListener {
        private final Song song;

        public SongButtonListener(Song song) {
            this.song = song;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JButton) {
                JButton clickedButton = (JButton) e.getSource();
                if (clickedButton.getText().equals(song.getSongName() + " By " + song.getArtist())) {
                    MusicPlayer musicPlayer = new MusicPlayer(song.getSongName(), song.getDuration(), song.getArtist());
                }
            }
        }
    }
}
