package com.crio.jukebox.repositories.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.exceptions.ResourceNotFoundException;
import com.crio.jukebox.repositories.interfaces.IAlbumRepository;

public class AblumRepository implements IAlbumRepository {

    private Map<String, Album> albumMap;
    int autoIncrement = 0;


    public AblumRepository() {
        this.albumMap = new HashMap<String, Album>();
    }

    public AblumRepository(Map<String, Album> albumMap) {
        this.albumMap = albumMap;
        this.autoIncrement = albumMap.size();
    }

    @Override
    public Album save(Album entity) {
        if(entity.getId() == null) {
            autoIncrement++;
            Album a = new Album(Integer.toString(autoIncrement), entity.getTitle(), entity.getArtist(), entity.getArtistGroup());
            albumMap.put(a.getId(), a);
            return a;
        }
        albumMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void deleteById(String id) {
        if(!albumMap.containsKey(id)) {
            throw new ResourceNotFoundException("Album with Id "+ id +" Not Found!!!");
        }
        albumMap.remove(id);
    }

    @Override
    public List<Album> findAll() {
        if(albumMap.isEmpty()) return new ArrayList<>();

        return albumMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Album findById(String id) {
        return albumMap.get(id);
    }

    @Override
    public Album findByTitle(String title) {
        // return albumMap.entrySet().stream()
        //                             .map(e -> e.getValue())
        //                             .filter(e -> e.getTitle().equals(title))
        //                             .findFirst()
        //                             .orElseThrow(() -> new ResourceNotFoundException("Album with Title "+ title +" Not Found!!!"));
        for(Entry<String, Album> entry: albumMap.entrySet()) {
            Album album = entry.getValue();
            if(album.getTitle().equalsIgnoreCase(title)){
                return album;
            }
        }
        return null;
    }
    
}
