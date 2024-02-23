package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.dto.PlaylistDto;
import com.crio.jukebox.dto.PlaylistModifyDto;

public interface IPlaylistService {
    PlaylistDto create(String userId, String title, List<String> songIds);
    void delete(String userId, String playlistId);
    PlaylistModifyDto addSong(String userId, String playlistId, List<String> songIds);
    PlaylistModifyDto deleteSong(String userId, String playlistId, List<String> songIds);
}
