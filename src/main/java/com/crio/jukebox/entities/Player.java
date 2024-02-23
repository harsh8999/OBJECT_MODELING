package com.crio.jukebox.entities;

import java.util.List;
import java.util.Stack;
import com.crio.jukebox.dataStructure.Node;


/**
*   The class Player is an implementation of Music Player
*   It can play any playlist with song in next, previous and specific song. 
*   @author  Harsh Nayak
*   @version 1.0
*   @since   2023-02-20 
*/

public class Player {

    Node head, tail;

    public void generateCircularDoublyLinkedList(List<Song> items) {
        head = new Node(items.get(0), null);
        head.next = head;
        head.previous = head;
        tail = head;
        for(int i = 1; i < items.size(); i++) {
            tail.next = new Node(items.get(i), tail);
            tail.next.next = head;
            tail = tail.next;
            head.previous = tail;
        }
    }

    public Playlist playlist;
    public Stack<Node> songStack;

    public Player(Playlist playlist) {
        this.playlist = playlist;
        this.songStack = new Stack<>();
        generateCircularDoublyLinkedList(playlist.getSongs());
    }

    public Node getHeadOfSongsCDLL() {
        return head;
    }

    public Node getTailOfSongsCDLL() {
        return tail;
    }
    
}
