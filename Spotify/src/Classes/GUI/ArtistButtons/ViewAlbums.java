package Classes.GUI.ArtistButtons;

import Classes.Album;
import Classes.GUI.jframes;
import Classes.Song;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ViewAlbums {

    public ViewAlbums() {

        jframes viewAlbumsPage = new jframes("My Albums");
        viewAlbumsPage.setLayout(new BorderLayout());

        JTable albumTable;

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(68, 184, 74));

        List<Album> albums = Album.loadAlbumDetails();

        //Create Table
        String[] columnNames = {"Album", "Artist", "Song"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        albumTable = new JTable(model);

        for (Album album : albums) {
            String albumName = album.getAlbumName();
            String artist = album.getArtist();

            for (Song song : album.getSongs()) {
                String songName = song.getSongName();
                model.addRow(new Object[]{albumName, artist, songName});
            }
        }




        JScrollPane scroll = new JScrollPane(albumTable);
        panel.add(scroll, BorderLayout.CENTER);

        // Add panel
        viewAlbumsPage.add(panel, BorderLayout.CENTER);
    }
}
