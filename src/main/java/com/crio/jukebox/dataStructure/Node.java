package com.crio.jukebox.dataStructure;

import com.crio.jukebox.entities.Song;

public class Node {
    public Song song;
    public Node previous;
    public Node next;

    public Node(Song song, Node previous) {
        this.song = song;
        this.previous = previous;
        this.next = null;
    }

    public Node(Song song, Node previous, Node next) {
        this.song = song;
        this.previous = previous;
        this.next = next;
    }
}
