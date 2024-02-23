package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.dto.PlaylistDto;
import com.crio.jukebox.dto.PlaylistModifyDto;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.ResourceNotFoundException;
import com.crio.jukebox.repositories.interfaces.IPlaylistRepository;
import com.crio.jukebox.repositories.interfaces.ISongRepository;
import com.crio.jukebox.repositories.interfaces.IUserRepository;


public class PlaylistServiceImpl implements IPlaylistService {

    private IPlaylistRepository playlistRepository;
    private ISongRepository songRepository;
    private IUserRepository userRepository;
    

    public PlaylistServiceImpl(IPlaylistRepository playlistRepository,
            ISongRepository songRepository, IUserRepository userRepository) {
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PlaylistDto create(String userId, String title, List<String> songIds) {
        User user = userRepository.findById(userId);
        if(user == null) {
            throw new ResourceNotFoundException("User with UserId "+userId+" Not Found!!!");
        }

        // Playlist playlist = playlistRepository.findByTitle(title);
        // Playlist userPlaylist = userRepository.getUsersPlaylist(userId, playlist.getId());

        // if(userPlaylist != null) {
        //     throw new UserHavePlayListException("User already have this playlist!!!");
        // }

        List<Song> songs = new ArrayList<>();
        for(String id: songIds) {
            Song song = songRepository.findById(id); // if song not found than exception would be thrown...
            if(song == null) {
                throw new ResourceNotFoundException("Song with songId "+ id +" Not Found!!!");
            }
            songs.add(song);
        }
        // Playlist p = new Playlist(null, userId, title, songIds);
        Playlist p = new Playlist(null, title, songs);
        Playlist savedPlaylist = playlistRepository.save(p);
        user.addPlaylist(savedPlaylist);
        PlaylistDto playlistDto = new PlaylistDto(savedPlaylist.getId());
        return playlistDto;
    }

    @Override
    public void delete(String userId, String playlistId) {
        User user = userRepository.findById(userId);
        if(user == null) {
            throw new ResourceNotFoundException("User with UserId "+userId+" Not Found!!!");
        }
        
        Playlist playlist = userRepository.getUsersPlaylist(userId, playlistId);

        if(playlist == null) {
            throw new ResourceNotFoundException("User with UserId "+ userId +" doesn't contain Playlist with Id "+ playlistId);
        }

        user.deletePlaylist(playlist);
        playlistRepository.deleteById(playlistId);
    }

    @Override
    public PlaylistModifyDto addSong(String userId, String playlistId, List<String> songIds) {
        User user = userRepository.findById(userId);
        if(user == null) {
            throw new ResourceNotFoundException("User with UserId "+userId+" Not Found!!!");
        }
        Playlist playlist = userRepository.getUsersPlaylist(userId, playlistId);

        if(playlist == null) {
            throw new ResourceNotFoundException("User with UserId "+ userId +" doesn't contain Playlist with Id "+ playlistId);
        }

        List<Song> songs = new ArrayList<>();
        for(String id: songIds) {
            Song song = songRepository.findById(id); // if song not found than exception would be thrown...
            if(song == null) {
                throw new ResourceNotFoundException("Song with songId "+ id +" Not Found!!!");
            } else {
                songs.add(song);
            }

        }

        playlist.addSongs(songs);
        Playlist updatedPlaylist = playlistRepository.save(playlist);
        List<String> savedSongIds = updatedPlaylist.getSongs().stream().map(e -> e.getId()).collect(Collectors.toList());
        PlaylistModifyDto playlistModifyDto = new PlaylistModifyDto(updatedPlaylist.getId(), updatedPlaylist.getTitle(), savedSongIds);
        return playlistModifyDto;
        
    }

    @Override
    public PlaylistModifyDto deleteSong(String userId, String playlistId, List<String> songIds) {
        User user = userRepository.findById(userId);
        if(user == null) {
            throw new ResourceNotFoundException("User with UserId "+userId+" Not Found!!!");
        }
        
        Playlist playlist = playlistRepository.findById(playlistId);
        if(playlist == null) {
            throw new ResourceNotFoundException("User with UserId "+ userId +" doesn't contain Playlist with Id "+ playlistId);
        }

        List<Song> songs = new ArrayList<>();
        for(String id: songIds) {
            Song song = songRepository.findById(id); 
            // if song not found than exception would be thrown...
            if(song == null) {
                throw new ResourceNotFoundException("Song with songId "+ id +" Not Found!!!");
            }else if (!playlist.getSongs().contains(song)) {
                throw new ResourceNotFoundException("Playlist with id "+playlistId+" doesn't contains Song with songId "+ id);
            } else {
                songs.add(song);
            }

        }

        playlist.removeSongs(songs);
        Playlist updatedPlaylist = playlistRepository.save(playlist);
        List<String> savedSongIds = updatedPlaylist.getSongs().stream().map(e -> e.getId()).collect(Collectors.toList());
        PlaylistModifyDto playlistModifyDto = new PlaylistModifyDto(updatedPlaylist.getId(), updatedPlaylist.getTitle(), savedSongIds);
        return playlistModifyDto;
    }
    
}
