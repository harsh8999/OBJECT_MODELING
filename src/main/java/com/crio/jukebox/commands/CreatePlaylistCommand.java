package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.dto.PlaylistDto;
import com.crio.jukebox.services.IPlaylistService;

public class CreatePlaylistCommand implements ICommand {
    
    private IPlaylistService playlistService;

    public CreatePlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    // Sample Input Token List:- ["CREATE-PLAYLIST", "1", "MY_PLAYLIST_1", "1", "4", "5", "6"]
    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String title = tokens.get(2);
        List<String> songIds = new ArrayList<>();
        for(int i = 3; i < tokens.size(); i++) {
            songIds.add(tokens.get(i));
        }
        PlaylistDto playlistDto = playlistService.create(userId, title, songIds);
        System.out.println(playlistDto);
    }

    
}
