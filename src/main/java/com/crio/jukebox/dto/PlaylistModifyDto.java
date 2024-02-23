package com.crio.jukebox.dto;

import java.util.List;

public class PlaylistModifyDto {
    // Playlist ID - 1
    // Playlist Name - MY_PLAYLIST_1
    // Song IDs - 1 4 5 6 7
    String id;
    String name;
    List<String> songIds;
    
    public PlaylistModifyDto(String id, String name, List<String> songIds) {
        this.id = id;
        this.name = name;
        this.songIds = songIds;
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<String> getSongIds() {
        return songIds;
    }
    public void setSongIds(List<String> songIds) {
        this.songIds = songIds;
    }
    
    @Override
    public String toString() {
        return "Playlist ID - "+ id +"\n" +
                "Playlist Name - "+ name + "\n" +
                "Song IDs - "+ String.join(" ", songIds);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((songIds == null) ? 0 : songIds.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlaylistModifyDto other = (PlaylistModifyDto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (songIds == null) {
            if (other.songIds != null)
                return false;
        } else if (!songIds.equals(other.songIds))
            return false;
        return true;
    }
}
