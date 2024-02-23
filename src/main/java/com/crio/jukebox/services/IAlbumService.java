package com.crio.jukebox.services;

import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.ArtistGroup;

public interface IAlbumService {
    Album create(String title, Artist artist, ArtistGroup artistGroup);
}
