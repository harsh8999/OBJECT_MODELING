package com.crio.jukebox.dataloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Song;

public class SongCsvLoader implements DataLoader<Song> {


    public SongCsvLoader() {
        
    }

    @Override
    public List<Song> load(String filename) {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename));) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        List<Song> songs = new ArrayList<>();

        for (List<String> record: records) {
            String title = record.get(0);
            String genre = record.get(1);
            String albumTitle = record.get(2);
            String albumArtistName = record.get(3);

            Artist albumArtist = new Artist(null, albumArtistName);

            String[] artistsArray = record.get(4).split("#");
            List<Artist> artists = new ArrayList<>();
            for(String artistName: artistsArray) {
                artists.add(new Artist(null, artistName));
            }

            Album album = new Album(null, albumTitle, albumArtist, null);

            songs.add(new Song(null, title, genre, album, artists));

        }

        return songs;
        
    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }
}
