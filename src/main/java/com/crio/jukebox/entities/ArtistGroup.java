package com.crio.jukebox.entities;

import java.util.List;

public class ArtistGroup extends BaseEntity {
    List<Artist> artists;
    
    public ArtistGroup(List<Artist> artists) {
        this.artists = artists;
        
    }

    public ArtistGroup(String id, List<Artist> artists) {
        this(artists);
        this.id = id;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

   
    @Override
    public String toString() {
        return "ArtistGroup [id=" + id +  ", artists=" + artists + "]";
    }

    
    
}
