package com.crio.jukebox.repository;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.implementations.AblumRepository;
import com.crio.jukebox.repositories.implementations.ArtistRepository;
import com.crio.jukebox.repositories.implementations.SongRepository;
import com.crio.jukebox.repositories.interfaces.IAlbumRepository;
import com.crio.jukebox.repositories.interfaces.IArtistRepository;
import com.crio.jukebox.repositories.interfaces.ISongRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Song Repository Test")
public class SongRepoTest {
    ISongRepository songRepository;
    IArtistRepository artistRepository;
    IAlbumRepository albumRepository;

    @BeforeEach
    public void setup() {
        songRepository = new SongRepository();
        artistRepository = new ArtistRepository();
        albumRepository = new AblumRepository();
    }

    @Test
    void createSong() {
        // 1,South of the Border,Pop,No.6 Collaborations Project,Ed Sheeran,Ed Sheeran#Cardi.B#Camilla Cabello
        String id = "1";
        String title = "South of the Border";
        String genre = "Pop";
        Artist albumArtist = artistRepository.save(new Artist(null, "Ed Sheeran"));
        Album album = albumRepository.save(new Album(null, "No.6 Collaborations Project", albumArtist, null));
        List<Artist> artists = new ArrayList<>();
        artists.add(artistRepository.findByName(albumArtist.getName()));
        artists.add(artistRepository.save(new Artist(null, "Cardi.B")));
        artists.add(artistRepository.save(new Artist(null, "Camilla Cabello")));
        Song song = new Song(id, title, genre, album, artists);
        Song savedSong = songRepository.save(song);
        System.out.println(savedSong);
    }
}
