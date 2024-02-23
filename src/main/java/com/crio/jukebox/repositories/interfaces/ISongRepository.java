package com.crio.jukebox.repositories.interfaces;

import com.crio.jukebox.entities.Song;

public interface ISongRepository extends CRUDRepository<Song, String> {
    Song findByTitle(String title);
}
