package com.crio.jukebox.repositories.interfaces;

import com.crio.jukebox.entities.Playlist;

public interface IPlaylistRepository extends CRUDRepository<Playlist, String>{
    Playlist findByTitle(String title);
}
