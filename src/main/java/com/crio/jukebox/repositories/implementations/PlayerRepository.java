package com.crio.jukebox.repositories.implementations;

import com.crio.jukebox.entities.Player;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.repositories.interfaces.IPlayerRepository;

public class PlayerRepository implements IPlayerRepository {
    private Player player;
    
    @Override
    public Player setPlayer(Playlist playlist) {
        player = new Player(playlist);
        return player;
    }

    @Override
    public Player getPlayer() {
        return player;
    }
    
}
