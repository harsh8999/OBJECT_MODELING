package com.crio.jukebox.repositories.interfaces;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.User;
// import java.util.List;
// import com.crio.jukebox.entities.Song;

public interface IUserRepository extends CRUDRepository<User, String> {
	Playlist getUsersPlaylist(String userId, String playlistId);
    // void createPlayList(String userId, String playlistTitle, List<Song> songs);
	// void removePlayList(String userId, String playlistId);
	// void addSongsToPlayList(String userId, String playlistId, List<Song> songs);
	// void removeSongFromPlayList(String userId, String playlistId, Song song);
}
