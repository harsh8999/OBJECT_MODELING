package com.crio.jukebox.repositories.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exceptions.ResourceNotFoundException;
import com.crio.jukebox.repositories.interfaces.IPlaylistRepository;

public class PlaylistRepository implements IPlaylistRepository {
    private Map<String, Playlist> playlistMap;
    int autoIncrement = 0;


    public PlaylistRepository() {
        this.playlistMap = new HashMap<String, Playlist>();
    }

    public PlaylistRepository(Map<String, Playlist> playlistMap) {
        this.playlistMap = playlistMap;
        this.autoIncrement = playlistMap.size();
    }

    @Override
    public Playlist save(Playlist entity) {
        if(entity.getId() == null) {
            autoIncrement++;
            // Playlist p = new Playlist(Integer.toString(autoIncrement), entity.getUserId(), entity.getTitle(), entity.getSongIds());
            Playlist p = new Playlist(Integer.toString(autoIncrement), entity.getTitle(), entity.getSongs());
            playlistMap.put(p.getId(), p);
            return p;
        }
        playlistMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void deleteById(String id) {
        if(!playlistMap.containsKey(id)) {
            throw new ResourceNotFoundException("PlayList with Id "+ id +" Not Found!!!");
        }
        playlistMap.remove(id);
    }

    @Override
    public List<Playlist> findAll() {
        if(playlistMap.isEmpty()) return new ArrayList<>();

        return playlistMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Playlist findById(String id) {
        return playlistMap.get(id);
    }

    @Override
    public Playlist findByTitle(String title) {
        return playlistMap.entrySet().stream()
                                    .map(e -> e.getValue())
                                    .filter(e -> e.getTitle().equals(title))
                                    .findFirst()
                                    .orElseThrow(() -> new ResourceNotFoundException("PlayList with Title "+ title+" Not Found!!!"));
    }

}
