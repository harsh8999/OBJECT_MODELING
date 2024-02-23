package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Song Test")
public class SongTest {
    @Test
    void createSong() {
        // 1,South of the Border,Pop,No.6 Collaborations Project,Ed Sheeran,Ed Sheeran#Cardi.B#Camilla Cabello
        String id = "1";
        String title = "South of the Border";
        String genre = "Pop";
        Artist albumArtist = new Artist("1", "Ed Sheeran");
        Album album = new Album("1", "No.6 Collaborations Project", albumArtist, null);

        List<Artist> artists = new ArrayList<>();
        artists.add(albumArtist);
        artists.add(new Artist("2", "Cardi.B"));
        artists.add(new Artist("3", "Camilla Cabello"));
        Song song = new Song(id, title, genre, album, artists);
        System.out.println(song);
    }
}
