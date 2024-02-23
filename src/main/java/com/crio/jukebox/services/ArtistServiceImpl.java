package com.crio.jukebox.services;

import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.repositories.interfaces.IArtistRepository;

public class ArtistServiceImpl implements IArtistService {
    private IArtistRepository artistRepository;

    public ArtistServiceImpl(IArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public Artist create(String name) {
        Artist artist = artistRepository.findByName(name);
        // if artist not present create artist
        if(artist == null) {
            artist = new Artist(null, name);
        }
        
        return artistRepository.save(artist);  
    }
}
