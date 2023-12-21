package Classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Album {

    private String albumName, artist;
    private List<Song> songs;

    public Album() {
        this.songs = new ArrayList<>();
    }

    public static List<Album> loadAlbumDetails() {
        List<Album> albums = new ArrayList<>();
        Album currentAlbum = null;

        try (FileReader fileReader = new FileReader("albumDetails.txt");
             BufferedReader reader = new BufferedReader(fileReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Album Name: ")) {
                    if (currentAlbum != null) {
                        albums.add(currentAlbum);
                    }
                    currentAlbum = new Album();
                    currentAlbum.setAlbumName(line.substring("Album Name: ".length()));
                } else if (line.startsWith("Artist: ")) {
                    if (currentAlbum != null) {
                        currentAlbum.setArtist(line.substring("Artist: ".length()));
                    }
                } else if (!line.equals("End of Album")) {
                    if (currentAlbum != null) {
                        Song song = new Song();
                        song.setSongName(line);
                        currentAlbum.addSong(song);
                    }
                }
            }

            // Add the last album
            if (currentAlbum != null) {
                albums.add(currentAlbum);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return albums;
    }

    public static void deleteAlbumFromFile(Album albumToDelete) {
        List<Album> albumData = loadAlbumDetails();

        try (FileWriter fileWriter = new FileWriter("albumDetails.txt");
             BufferedWriter writer = new BufferedWriter(fileWriter)) {

            for (Album album : albumData) {
                if (album.getAlbumName().equals(albumToDelete.getAlbumName())) {
                    continue;  // Mark the deleted album
                }

                writer.write("Album Name: " + album.getAlbumName());
                writer.newLine();
                writer.write("Artist: " + album.getArtist());
                writer.newLine();

                for (Song song : album.getSongs()) {
                    writer.write(song.getSongName());
                    writer.newLine();
                }

                writer.write("End of Album"); // Mark the end of each album
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //Doesn't write the deleted album in file

    public static List<Album> getAlbumsByArtist(String artistName) {
        List<Album> allAlbums = loadAlbumDetails();
        List<Album> albumsByArtist = new ArrayList<>();
        for (Album album : allAlbums) {
            if (album.getArtist().equals(artistName)) {
                albumsByArtist.add(album);
            }
        }
        return albumsByArtist;
    }

    public static void updateAlbumDetails(List<Album> albums, String oldArtisticName, String newArtisticName) {
        List<Album> allAlbums = loadAlbumDetails();

        try (FileWriter fileWriter = new FileWriter("albumDetails.txt");
             BufferedWriter writer = new BufferedWriter(fileWriter)) {

            for (Album album : allAlbums) {
                writer.write("Album Name: " + album.getAlbumName());
                writer.newLine();
                if (album.getArtist().equals(oldArtisticName)) {
                    writer.write("Artist: " + newArtisticName);
                    writer.newLine();
                } else {
                    writer.write("Artist: " + album.getArtist());
                    writer.newLine();
                }

                for (Song song : album.getSongs()) {
                    writer.write(song.getSongName());
                    writer.newLine();
                }

                writer.write("End of Album");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //When Artistic name changes

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void saveAlbumDetails(String albumName, String artist, List<String> songNames) {
        try (FileWriter fileWriter = new FileWriter("albumDetails.txt", true);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {

            writer.write("Album Name: " + albumName);
            writer.newLine();
            writer.write("Artist: " + artist);
            writer.newLine();

            for (String song : songNames) {
                writer.write(song);
                writer.newLine();
            }

            writer.write("End of Album"); // Mark end of each album
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isAlbumSaved(String albumN) {
        List<Album> savedAlbums = loadAlbumDetails();
        for (Album album : savedAlbums) {
            if (album.getAlbumName().equals(albumN)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSongInAlbum(String songName) {
        List<Album> alb = loadAlbumDetails();

        for (Album album : alb) {
            if (album.getSongs().equals(songName)){
                return true;
            }
        }
        return false;
    }

    public String AlbumNameBySong(String songN){
        List<Album> alb=loadAlbumDetails();
        for (Album album : alb) {
            for (Song song : album.getSongs()) {
                if (song.getSongName().equals(songN)) {
                    return album.getAlbumName();
                }
            }
        }
        return null;
    } //For Inside Music Player


}
