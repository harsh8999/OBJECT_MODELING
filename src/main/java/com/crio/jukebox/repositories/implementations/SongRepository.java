package com.crio.jukebox.repositories.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.ResourceNotFoundException;
import com.crio.jukebox.repositories.interfaces.ISongRepository;

public class SongRepository implements ISongRepository {

    private Map<String, Song> songMap;
    private int autoIncrement = 0;

    public SongRepository() {
        this.songMap = new HashMap<String, Song>();
    }

    public SongRepository(Map<String, Song> songMap) {
        this.songMap = songMap;
        this.autoIncrement = songMap.size();
    }

    @Override
    public Song save(Song entity) {
        if(entity.getId() == null) {
            autoIncrement++;
            Song s = new Song(Integer.toString(autoIncrement), entity.getTitle(), entity.getGenre(), entity.getAlbum(), entity.getArtists());
            songMap.put(s.getId(), s);
            return s;
        }
        songMap.put(entity.getId(), entity);
        return entity; 
    }

    @Override
    public void deleteById(String id) {
        songMap.remove(id);
    }

    @Override
    public List<Song> findAll() {
        if(songMap.isEmpty()) return new ArrayList<>();

        return songMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Song findById(String id) {
        return songMap.get(id);
    }

    @Override
    public Song findByTitle(String title) {
        return songMap.entrySet().stream()
                                    .map(e -> e.getValue())
                                    .filter(e -> e.getTitle().equals(title))
                                    .findFirst()
                                    .orElseThrow(() -> new ResourceNotFoundException("Song with song title "+ title+" Not Found!!!"));
    }
    
}
