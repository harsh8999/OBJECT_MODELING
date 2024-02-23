package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseEntity {

    private String name;
    private List<Playlist> playlists;

    public User(String id, String name) {
        this.name = name;
        this.playlists = new ArrayList<>();
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }


    public void addPlaylist(Playlist playlist) {
        if(!playlists.contains(playlist)) {
            playlists.add(playlist);
        }
    }

    public void deletePlaylist(Playlist playlist) {
        this.playlists.remove(playlist);
    }

    @Override
    public String toString() {
        return "User [id=" + id +", name=" + name + ", playlists=" + playlists + "]";
    }
    
}
