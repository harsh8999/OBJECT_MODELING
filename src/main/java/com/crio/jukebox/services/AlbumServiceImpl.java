package com.crio.jukebox.services;

import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.ArtistGroup;
import com.crio.jukebox.repositories.interfaces.IAlbumRepository;

public class AlbumServiceImpl implements IAlbumService {

    private IAlbumRepository albumRepository;

    public AlbumServiceImpl(IAlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public Album create(String title, Artist artist, ArtistGroup artistGroup) {
        Album album = albumRepository.findByTitle(title);
        // if album not present create album
        if(album == null) {
            album = new Album(null, title, artist, artistGroup);
        }
        
        return albumRepository.save(album);  
    }  
}
