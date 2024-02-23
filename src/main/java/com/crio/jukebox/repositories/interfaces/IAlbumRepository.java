package com.crio.jukebox.repositories.interfaces;

import com.crio.jukebox.entities.Album;

public interface IAlbumRepository extends CRUDRepository<Album, String> {
    Album findByTitle(String title);
}
