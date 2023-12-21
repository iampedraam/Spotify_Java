package Classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Song {

    int duration;
    private String songName, artist;

    public void saveSongToFile() {
        try (FileWriter fileWriter = new FileWriter("songs.txt", true);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write(songName + ";" + artist + ";" + duration);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Song> loadSongsFromFile() {
        List<Song> songs = new ArrayList<>();
        try (FileReader fileReader = new FileReader("songs.txt");
             BufferedReader reader = new BufferedReader(fileReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 3) {
                    Song song = new Song();
                    song.setSongName(data[0]);
                    song.setArtist(data[1]);
                    song.setDuration(Integer.valueOf(data[2]));
                    songs.add(song);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return songs;
    }

    public static void deleteSongFromFile(String songNameToDelete) {
        List<Song> songs = loadSongsFromFile();

        try (FileWriter fileWriter = new FileWriter("songs.txt");
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            for (Song song : songs) {
                if (!song.getSongName().equals(songNameToDelete)) {
                    writer.write(song.getSongName() + ";" + song.getArtist() + ";" + song.getDuration());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //Don't write deleted song


    public static List<Song> getSongsByArtist(String artistName) {
        List<Song> allSongs = loadSongsFromFile();
        List<Song> songsByArtist = new ArrayList<>();
        for (Song song : allSongs) {
            if (song.getArtist().equals(artistName)) {
                songsByArtist.add(song);
            }
        }
        return songsByArtist;
    } //Song Search by Artist

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isSongSaved(String songN) {
        List<Song> savedSongs = loadSongsFromFile();
        for (Song song : savedSongs) {
            if (song.getSongName().equals(songN)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSongForArtist(String songN, String artistName) {
        List<Song> savedSongs = loadSongsFromFile();
        for (Song song : savedSongs) {
            if (song.getSongName().equals(songN) && song.getArtist().equals(artistName)) {
                return true; // Song is for the artist
            }
        }
        return false;
    } // For Creating Album checks if song is for the artist

    public Song getSongFromName(String songName) {
        List<Song> savedSongs = loadSongsFromFile();
        for (Song song : savedSongs) {
            if (song.getSongName().equals(songName)) {
                return song;
            }
        }
        return null;
    }

    public static void updateSongArtist(String songName, String newArtistName) {
        List<Song> songs = loadSongsFromFile();

        try (FileWriter fileWriter = new FileWriter("songs.txt");
             BufferedWriter writer = new BufferedWriter(fileWriter)) {

            for (Song song : songs) {
                if (song.getSongName().equals(songName)) {
                    song.setArtist(newArtistName);
                }
                writer.write(song.getSongName() + ";" + song.getArtist() + ";" + song.getDuration());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //When Artistic Name Changes
}
