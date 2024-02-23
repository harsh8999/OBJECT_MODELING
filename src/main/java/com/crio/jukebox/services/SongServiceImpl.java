package com.crio.jukebox.services;

import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.dto.SongDto;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.interfaces.ISongRepository;

public class SongServiceImpl implements ISongService{

    private ISongRepository songRepository;

    
    public SongServiceImpl(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }


    @Override
    public SongDto createSong(String id, String title, String genre, 
            Album album, List<Artist> artists) {
        Song song = new Song(id, title, genre, album, artists);
        Song savedSong = this.songRepository.save(song);
        List<String> savedArtistIds = savedSong.getArtists().stream().map(e -> e.getName()).collect(Collectors.toList());
        return new SongDto(savedSong.getTitle(), savedSong.getAlbum().getTitle(), savedArtistIds);
    }
    
}
