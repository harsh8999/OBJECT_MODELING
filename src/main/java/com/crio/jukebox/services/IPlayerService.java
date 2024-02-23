package com.crio.jukebox.services;

import com.crio.jukebox.dto.SongDto;

public interface IPlayerService {
    SongDto playPlayList(String userId, String playlistId);
    SongDto playNextSong();
    SongDto playPrevSong();
    SongDto playSong(String songId);
}
