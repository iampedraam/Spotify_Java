package Classes.GUI;

import Classes.Album;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicPlayer {

    JButton play = new JButton("Play");
    JButton stop = new JButton("Stop");
    Timer timer;
    boolean isStopped = false;

    public MusicPlayer(String musicName, int durationInSeconds, String artist) {
        Album alb = new Album();

        boolean foundAlbum = alb.isSongInAlbum(musicName);
        String albumName = alb.AlbumNameBySong(musicName);


        jframes musicPlayerPage = new jframes(musicName + " by " + artist);
        musicPlayerPage.setSize(350, 350);

        // Unknown song image
        ImageIcon originalIcon = new ImageIcon("unknownMusic.jpg");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel image = new JLabel(resizedIcon);


        // Progress Bar
        JProgressBar progressBar = new JProgressBar(0, durationInSeconds);
        progressBar.setStringPainted(true);


        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        play.setFocusable(false);

        //Album Name
        JLabel album = new JLabel("Album: " + albumName);
        buttonPanel.add(album);

        //Duration
        JLabel duration = new JLabel("Song Duration: " + durationInSeconds + " S");
        buttonPanel.add(duration);


        //Progress Bar
        play.addActionListener(e -> {
            timer = new Timer(1000, new ActionListener() { //refresh every 1000 miliseconds (every 1 second)
                 int elapsedSeconds = 0;

                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (!isStopped) {
                        progressBar.setValue(elapsedSeconds++);
                        progressBar.setString(elapsedSeconds + "s");

                        if (elapsedSeconds > durationInSeconds) {
                            ((Timer) evt.getSource()).stop();
                            progressBar.setValue(durationInSeconds);
                            progressBar.setString(durationInSeconds + " s");
                        }
                    }
                }
            });
            timer.start();
        });
        buttonPanel.add(play);

        // Stop Button
        stop.setFocusable(false);
        stop.addActionListener(e -> {
            isStopped = !isStopped;
            if (isStopped) {
                timer.stop();
            } else {
                timer.start();
            }
        });
        buttonPanel.add(stop);


        musicPlayerPage.add(image, BorderLayout.NORTH);
        musicPlayerPage.add(buttonPanel, BorderLayout.CENTER);
        musicPlayerPage.add(progressBar, BorderLayout.SOUTH);
    }
}
