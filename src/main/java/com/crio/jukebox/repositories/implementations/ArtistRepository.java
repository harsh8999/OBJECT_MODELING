package com.crio.jukebox.repositories.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.repositories.interfaces.IArtistRepository;

public class ArtistRepository implements IArtistRepository {

    private Map<String, Artist> artistMap;
    private int autoIncrement = 0;

    public ArtistRepository(Map<String, Artist> artistMap, int autoIncrement) {
        this.artistMap = artistMap;
        this.autoIncrement = autoIncrement;
    }

    public ArtistRepository() {
        this.artistMap = new HashMap<String, Artist>();
    }

    @Override
    public Artist save(Artist entity) {
        if(entity.getId() == null) {
            autoIncrement++;
            Artist a = new Artist(Integer.toString(autoIncrement), entity.getName());
            artistMap.put(a.getId(), a);
            return a;
        }
        artistMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void deleteById(String id) {
        artistMap.remove(id);
    }

    @Override
    public List<Artist> findAll() {
        if(artistMap.isEmpty()) return new ArrayList<>();
        return artistMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Artist findById(String id) {
        return artistMap.get(id);
    }

    @Override
    public Artist findByName(String name) {
        for(Entry<String, Artist> entry: artistMap.entrySet()) {
            Artist artist = entry.getValue();
            if(artist.getName().equalsIgnoreCase(name)){
                return artist;
            }
        }
        return null;
    }
    
}
