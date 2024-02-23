package com.crio.jukebox.services;

import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.dataStructure.Node;
import com.crio.jukebox.dto.SongDto;
import com.crio.jukebox.entities.Player;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.ResourceNotFoundException;
import com.crio.jukebox.repositories.interfaces.IPlayerRepository;
import com.crio.jukebox.repositories.interfaces.IPlaylistRepository;

public class PlayerServiceImpl implements IPlayerService {
    private IPlayerRepository playerRepository;
    private IPlaylistRepository playlistRepository;

    private Node currSongPlaying;

    public PlayerServiceImpl(IPlayerRepository playerRepository,
            IPlaylistRepository playlistRepository) {
        this.playerRepository = playerRepository;
        this.playlistRepository = playlistRepository;
        this.currSongPlaying = null;
    }

    @Override
    public SongDto playPlayList(String userId, String playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId);
        if(playlist == null) {
            throw new ResourceNotFoundException("Playlist with Id "+playlistId+" Not Found!!!");
        }
        Player player = playerRepository.setPlayer(playlist);

        currSongPlaying = player.getHeadOfSongsCDLL();
        player.songStack.push(currSongPlaying);
        
        Song songPlaying = player.songStack.peek().song;
        
        List<String> songArtists = songPlaying.getArtists().stream().map(e -> e.getName()).collect(Collectors.toList());
        SongDto songDto = new SongDto(songPlaying.getTitle(), songPlaying.getAlbum().getTitle(), songArtists);
        return songDto;
    }

    @Override
    public SongDto playNextSong() {
        Player player = playerRepository.getPlayer();
        currSongPlaying = currSongPlaying.next;
        player.songStack.push(currSongPlaying);
        Song songPlaying = player.songStack.peek().song;
        List<String> songArtists = songPlaying.getArtists().stream().map(e -> e.getName()).collect(Collectors.toList());
        SongDto songDto = new SongDto(songPlaying.getTitle(), songPlaying.getAlbum().getTitle(), songArtists);
        return songDto;
    }

    @Override
    public SongDto playPrevSong() {
        Player player = playerRepository.getPlayer();
        player.songStack.pop();  // remove the current playing song
        if(player.songStack.isEmpty()) {
            currSongPlaying = currSongPlaying.previous;
            player.songStack.push(currSongPlaying);
        } else {
            currSongPlaying = player.songStack.peek(); // move current pointer to the playing song
        }
        Song songPlaying = player.songStack.peek().song;
        List<String> songArtists = songPlaying.getArtists().stream().map(e -> e.getName()).collect(Collectors.toList());
        SongDto songDto = new SongDto(songPlaying.getTitle(), songPlaying.getAlbum().getTitle(), songArtists);
        return songDto;
    }

    @Override
    public SongDto playSong(String songId) {
        Player player = playerRepository.getPlayer();
        Node head = player.getHeadOfSongsCDLL();
        Node tail = player.getTailOfSongsCDLL();

        currSongPlaying = null;
        player.songStack.removeAllElements();

        if(head.song.getId().equals(songId)) {
            currSongPlaying = head;
        } else if(tail.song.getId().equals(songId)) {
            currSongPlaying = tail;
        } else {
            Node curr = head;
            while(curr != tail) {
                if(curr.song.getId().equals(songId)) {
                    currSongPlaying = curr;
                    break;
                }
                curr = curr.next;
            }
        }
        if(currSongPlaying == null) {
            throw new ResourceNotFoundException("Given song id is not a part of the active playlist");
        }
        player.songStack.push(currSongPlaying);
        Song songPlaying = player.songStack.peek().song;
        List<String> songArtists = songPlaying.getArtists().stream().map(e -> e.getName()).collect(Collectors.toList());
        SongDto songDto = new SongDto(songPlaying.getTitle(), songPlaying.getAlbum().getTitle(), songArtists);
        return songDto;
    }
    
}
