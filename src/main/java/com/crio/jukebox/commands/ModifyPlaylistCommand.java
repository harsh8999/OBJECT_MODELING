package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.dto.PlaylistModifyDto;
import com.crio.jukebox.services.IPlaylistService;

public class ModifyPlaylistCommand implements ICommand {

    private IPlaylistService playlistService;

    public ModifyPlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }


    // token = ["MODIFY-PLAYLIST", "ADD-SONG", "1", "1", "7"]
    // token = ["MODIFY-PLAYLIST", "DELETE-SONG", "1", "1", "7"]
    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(2);
        String playlistId = tokens.get(3);
        List<String> songIds = new ArrayList<>();
        for(int i = 4; i < tokens.size(); i++) {
            songIds.add(tokens.get(i));
        }

        PlaylistModifyDto playlistModifyDto;
        if(tokens.get(1).equals("ADD-SONG")) {
            playlistModifyDto = playlistService.addSong(userId, playlistId, songIds);
            System.out.println(playlistModifyDto);
        }
        else if(tokens.get(1).equals("DELETE-SONG")) {
            playlistModifyDto = playlistService.deleteSong(userId, playlistId, songIds);
            System.out.println(playlistModifyDto);
        }
    }
    
}
