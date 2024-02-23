package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Playlist Test")
public class PlaylistTest {
    @Test
    void createPlaylist() {
        List<Song> songs = new ArrayList<>();
        songs.add(new Song("1", null, null, null, null));
        songs.add(new Song("4", null, null, null, null));
        songs.add(new Song("5", null, null, null, null));
        songs.add(new Song("6", null, null, null, null));
        Playlist playlist = new Playlist("1", "MY_PLAYLIST_1", songs);
        System.out.println(playlist);
    }
}
