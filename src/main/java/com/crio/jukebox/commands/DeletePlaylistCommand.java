package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IPlaylistService;

public class DeletePlaylistCommand implements ICommand {
    private IPlaylistService playlistService;

    public DeletePlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    // token = ["DELETE-PLAYLIST", "1" , "2"]
    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String playlistId = tokens.get(2);
        playlistService.delete(userId, playlistId);
        System.out.println("Delete Successful");
    }
}
