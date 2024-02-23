package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.dto.SongDto;
import com.crio.jukebox.services.IPlayerService;

public class PlayPlaylistCommand implements ICommand {

    private IPlayerService playerService;

    public PlayPlaylistCommand(IPlayerService playerService) {
        this.playerService = playerService;
    }

    // sample token: ["PLAY-PLAYLIST", "1", "1"]
    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String playlistId = tokens.get(2);
        SongDto songDto = playerService.playPlayList(userId, playlistId);
        System.out.println("Current Song Playing");
        System.out.println(songDto);
    }
    
}
