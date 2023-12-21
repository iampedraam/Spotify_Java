package Classes.GUI.ArtistButtons;

import Classes.GUI.jframes;
import Classes.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteSong implements ActionListener {

    jframes deleteSongPage = new jframes("Remove Song");
    JTextField songNF = new JTextField();
    JButton delete = new JButton("delete Song");

    public DeleteSong() {

        deleteSongPage.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(68, 184, 74));

        GridBagConstraints loc = new GridBagConstraints();
        loc.insets = new Insets(20, 20, 20, 20);

        Dimension size = new Dimension(120, 30);


        //Song name to delete
        JLabel songName = new JLabel("Song Name:");
        loc.gridx = 0;
        loc.gridy = 0;
        panel.add(songName, loc);
        songNF.setPreferredSize(size);
        loc.gridx = 1;
        panel.add(songNF, loc);

        //button
        delete.setFocusable(false);
        delete.addActionListener(this);
        delete.setSize(200, 200);
        loc.gridx = 0;
        loc.gridy = 1;
        panel.add(delete, loc);

        //add panel to frame
        deleteSongPage.add(panel, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delete) {
            Song newS = new Song();
            String songNameEntered = songNF.getText().toString();

            if (newS.isSongSaved(songNameEntered) == true) {
                newS.deleteSongFromFile(songNameEntered);
                JOptionPane.showMessageDialog(null, "Song deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                deleteSongPage.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Song Not Found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
