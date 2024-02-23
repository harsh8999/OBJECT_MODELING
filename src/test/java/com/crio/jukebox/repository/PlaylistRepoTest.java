package com.crio.jukebox.repository;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.implementations.PlaylistRepository;
import com.crio.jukebox.repositories.interfaces.IPlaylistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Playlist Repository Test")
public class PlaylistRepoTest {
    IPlaylistRepository playlistRepository;

    @BeforeEach 
    public void setup() {
        playlistRepository = new PlaylistRepository();
    }

    @Test
    void createPlaylist() {
        List<Song> songs = new ArrayList<>();
        songs.add(new Song("1", null, null, null, null));
        songs.add(new Song("4", null, null, null, null));
        songs.add(new Song("5", null, null, null, null));
        songs.add(new Song("6", null, null, null, null));
        Playlist playlist = new Playlist(null, "MY_PLAYLIST_1", songs);
        Playlist savedPlaylist = playlistRepository.save(playlist);
        System.out.println(savedPlaylist);
    }
}
