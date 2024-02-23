package com.crio.jukebox.entities;


public class Artist extends BaseEntity {

    private String name;

    public Artist(String name) {
        this.name = name;
    }

    public Artist(String id, String name) {
        this(name);
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Artist [id=" + id + ", name=" + name + "]";
    }
}
