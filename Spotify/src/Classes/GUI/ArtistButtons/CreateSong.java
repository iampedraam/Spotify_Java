package Classes.GUI.ArtistButtons;

import Classes.DataBase;
import Classes.GUI.jframes;
import Classes.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateSong implements ActionListener {

    jframes createSongPage = new jframes("Add a new Song");
    JTextField songNF = new JTextField();
    JTextField songDurationFS = new JTextField();
    JButton addSong = new JButton("Submit");

    public CreateSong() {

        createSongPage.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(68, 184, 74));

        GridBagConstraints loc = new GridBagConstraints();
        loc.gridx = 0;
        loc.insets = new Insets(20, 20, 20, 20);

        Dimension size = new Dimension(120, 30);

        //Song Name
        JLabel songName = new JLabel("Song Name:");
        loc.gridy = 0;
        panel.add(songName, loc);
        songNF.setPreferredSize(size);
        loc.gridx = 1;
        panel.add(songNF, loc);

        //Duration
        JLabel durationS = new JLabel("Song Duration (Second):");
        loc.gridx = 0;
        loc.gridy = 1;
        panel.add(durationS, loc);
        songDurationFS.setPreferredSize(size);
        loc.gridx = 1;
        panel.add(songDurationFS, loc);

        //Submit Button
        addSong.setFocusable(false);
        addSong.addActionListener(this);
        addSong.setSize(200, 200);
        loc.gridx = 0;
        loc.gridy = 3;
        panel.add(addSong, loc);

        //add panel to JFrame
        createSongPage.add(panel, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addSong) {
            Song newSong = new Song();
            newSong.setSongName(songNF.getText());
            newSong.setArtist(DataBase.getArtName(DataBase.getCurrentUserIndex()));
            newSong.setDuration(Integer.valueOf(songDurationFS.getText()));
            newSong.saveSongToFile();
            JOptionPane.showMessageDialog(null,"Song Created Successfully!","Song created",JOptionPane.INFORMATION_MESSAGE);
            createSongPage.dispose();
        }
    }
}
