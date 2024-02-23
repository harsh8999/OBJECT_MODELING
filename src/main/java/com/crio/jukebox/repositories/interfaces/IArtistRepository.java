package com.crio.jukebox.repositories.interfaces;

import com.crio.jukebox.entities.Artist;

public interface IArtistRepository extends CRUDRepository<Artist, String> {

    Artist findByName(String name);
}
