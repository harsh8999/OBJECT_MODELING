package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.dto.SongDto;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;


public interface ISongService {
    SongDto createSong(String id, String title, String genre, 
        Album album, List<Artist> artists);
}
