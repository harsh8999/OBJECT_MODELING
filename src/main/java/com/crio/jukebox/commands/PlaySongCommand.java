package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.dto.SongDto;
import com.crio.jukebox.exceptions.ResourceNotFoundException;
import com.crio.jukebox.services.IPlayerService;

public class PlaySongCommand implements ICommand{

    private IPlayerService playerService;
    
    public PlaySongCommand(IPlayerService playerService) {
        this.playerService = playerService;
    }

    // sample token: 
    @Override
    public void execute(List<String> tokens) {
        SongDto songDto;
        if(tokens.get(2).equalsIgnoreCase("BACK")) {
            songDto = playerService.playPrevSong(); 
            System.out.println("Current Song Playing");  
            System.out.println(songDto);
        } else if (tokens.get(2).equalsIgnoreCase("NEXT")) {
            songDto = playerService.playNextSong();
            System.out.println("Current Song Playing");
            System.out.println(songDto);
        } else {
            try {
                songDto = playerService.playSong(tokens.get(2));
                System.out.println("Current Song Playing");
                System.out.println(songDto);
            } catch (ResourceNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
}
