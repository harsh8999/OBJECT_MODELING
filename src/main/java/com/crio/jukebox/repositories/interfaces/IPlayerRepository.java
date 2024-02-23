package com.crio.jukebox.repositories.interfaces;

import com.crio.jukebox.entities.Player;
import com.crio.jukebox.entities.Playlist;

public interface IPlayerRepository {
    Player setPlayer(Playlist playlist);
    Player getPlayer();
}
