package com.crio.jukebox.entities;

public class Album extends BaseEntity {
    private String title;
    private Artist artist;
    // private List<Artist> featuredArtists;
    private ArtistGroup artistGroup;
    
    public Album(String id, String title, Artist artist, ArtistGroup artistGroup) {
        this.title = title;
        this.artist = artist;
        // this.featuredArtists = new ArrayList<>();
        this.artistGroup = artistGroup;
        this.id = id;
    }
    @Override
    public String toString() {
        // return "Album [artist=" + artist + ", artistGroup=" + artistGroup + ", featuredArtists="
        //         + featuredArtists + ", title=" + title + "]";
        return "Album [artist=" + artist + ", artistGroup=" + artistGroup +
                ", title=" + title + "]";
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Artist getArtist() {
        return artist;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
    // public List<Artist> getFeaturedArtists() {
    //     return featuredArtists;
    // }
    // public void setFeaturedArtists(List<Artist> featuredArtists) {
    //     this.featuredArtists = featuredArtists;
    // }
    public ArtistGroup getArtistGroup() {
        return artistGroup;
    }
    public void setArtistGroup(ArtistGroup artistGroup) {
        this.artistGroup = artistGroup;
    }
    
    
}
