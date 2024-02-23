package com.crio.jukebox.service;

import java.util.ArrayList;
import java.util.List;

import com.crio.jukebox.dto.PlaylistDto;
import com.crio.jukebox.dto.PlaylistModifyDto;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.ResourceNotFoundException;
import com.crio.jukebox.repositories.implementations.AblumRepository;
import com.crio.jukebox.repositories.implementations.ArtistRepository;
import com.crio.jukebox.repositories.implementations.PlaylistRepository;
import com.crio.jukebox.repositories.implementations.SongRepository;
import com.crio.jukebox.repositories.implementations.UserRepository;
import com.crio.jukebox.repositories.interfaces.IAlbumRepository;
import com.crio.jukebox.repositories.interfaces.IArtistRepository;
import com.crio.jukebox.repositories.interfaces.IPlaylistRepository;
import com.crio.jukebox.repositories.interfaces.ISongRepository;
import com.crio.jukebox.repositories.interfaces.IUserRepository;
import com.crio.jukebox.services.AlbumServiceImpl;
import com.crio.jukebox.services.ArtistServiceImpl;
import com.crio.jukebox.services.IAlbumService;
import com.crio.jukebox.services.IArtistService;
import com.crio.jukebox.services.IPlaylistService;
import com.crio.jukebox.services.ISongService;
import com.crio.jukebox.services.PlaylistServiceImpl;
import com.crio.jukebox.services.SongServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Playlist Service Test")
public class PlaylistServiceTest {
    private IPlaylistService playlistService;
    private IPlaylistRepository playlistRepository ;
    private ISongService songService ;
    private ISongRepository songRepository;
    private IAlbumRepository albumRepository;
    private IAlbumService albumService;
    private IArtistRepository artistRepository;
    private IArtistService artistService;
    private IUserRepository userRepository;
    private User user;

    
    @BeforeEach
    void setup() {
        playlistRepository = new PlaylistRepository();
        songRepository = new SongRepository();
        userRepository = new UserRepository();
        songService = new SongServiceImpl(songRepository);
        playlistService = new PlaylistServiceImpl(playlistRepository, songRepository, userRepository);
        albumRepository = new AblumRepository();
        albumService = new AlbumServiceImpl(albumRepository);
        artistRepository = new ArtistRepository();
        artistService = new ArtistServiceImpl(artistRepository);
        

        user = new User("1", "Harsh");
        userRepository.save(user);

        // add songs
        String file = "1,South of the Border,Pop,No.6 Collaborations Project,Ed Sheeran,Cardi.B#Ed Sheeran#Camilla Cabello\n"+
                        "2,I Dont'Care,Pop,No.6 Collaborations Project,Ed Sheeran,Justin Bieber\n"+
                        "3,Remember The Name,Pop,No.6 Collaborations Project,Ed Sheeran,Ed Sheeran#Eminem#50Cent\n"+
                        "4,Way To Break My Heart,Pop,No.6 Collaborations Project,Ed Sheeran,Ed Sheeran#Skrillex\n"+
                        "5,Cross Me,Pop,No.6 Collaborations Project,Ed Sheeran,Ed Sheeran#Chance The Rapper#PnB Rock\n"+
                        "6,Give Life Back To Music,Electronic Dance Music,Random Access Memories,Daft Punk,Daft Punk#Nile Rodgers\n"+
                        "7,Instant Crush,Electronic Dance Music,Random Access Memories,Daft Punk,Daft Punk#Julian Casablancas\n"+
                        "8,Get Lucky,Electronic Dance Music,Random Access Memories,Daft Punk,Daft Punk#Nile Rodgers#Pharrell Williams\n"+
                        "9,Lose Yourself To Dance,Electronic Dance Music,Random Access Memories,Daft Punk,Daft Punk#Nile Rodgers#Pharrell William\n"+
                        "10,Giorgio by Moroder,Electronic Dance Music,Random Access Memories,Daft Punk,Daft Punk#Giorgio Moroder\n"+
                        "11,Something In the Way,Rock,Nevermind,Nirvana,Nirvana\n"+
                        "12,Lithium,Rock,Nevermind,Nirvana,Nirvana\n"+
                        "13,Come as You Are,Rock,Nevermind,Nirvana,Nirvana\n"+
                        "14,Stay Away,Rock,Nevermind,Nirvana,Nirvana\n"+
                        "15,Lounge Act,Rock,Nevermind,Nirvana,Nirvana\n"+
                        "16,BLOOD.,Hip-Hop,DAMN.,Kendrick Lamar,Kendrick Lamar\n"+
                        "17,GOD.,Hip-Hop,DAMN.,Kendrick Lamar,Kendrick Lamar\n"+
                        "18,PRIDE.,Hip-Hop,DAMN.,Kendrick Lamar,Kendrick Lamar\n"+
                        "19,YAH.,Hip-Hop,DAMN.,Kendrick Lamar,Kendrick Lamar\n"+
                        "20,DUCKWORTH.,Hip-Hop,DAMN.,Kendrick Lamar,Kendrick Lamar\n"+
                        "21,Flamenco Sketches,Jazz,Kind of Blue,Miles Davis,Miles Davis\n"+
                        "22,So What,Jazz,Kind of Blue,Miles Davis,Miles Davis\n"+
                        "23,Freddie Freeloader,Jazz,Kind of Blue,Miles Davis,Miles Davis\n"+
                        "24,Blue in Green,Jazz,Kind of Blue,Miles Davis,Miles Davis\n"+
                        "25,All Blues,Jazz,Kind of Blue,Miles Davis,Miles Davis\n"+
                        "26,The Show Must Go on,Rock,Innuendo,Queen,Queen\n"+
                        "27,Somebody To Love,Rock,A Day at the Races,Queen,Queen\n"+
                        "28,Killer Queen,Rock,Sheer Heart Attack,Queen,Queen\n"+
                        "29,Another One Bites the Dust,Rock,The Game,Queen,Queen\n"+
                        "30,Bohemian Rhapsody,Rock,A Night at the Opera,Queen,Queen";
        String[] songs = file.split("\n");

        for(String song : songs) {
            String[] parts = song.split(",");
            
            String id = parts[0];
            String title = parts[1];
            String genre = parts[2];
            String albumTitle = parts[3];
            String albumArtistName = parts[4];
            Artist albumArtist = artistService.create(albumArtistName);
            // create album
            Album album = albumService.create(albumTitle, albumArtist, null);
            
            String[] artistsArray = parts[5].split("#");
            List<Artist> artists = new ArrayList<>();
            
            for(String artistName: artistsArray) {
                artists.add(artistService.create(artistName));
            }
        
            songService.createSong(id, title, genre, album, artists);
        }
    }

    @Test
    @DisplayName("Create Playlist")
    void createPlaylist() {
        
        
        List<String> songIds = new ArrayList<>();
        songIds.add("1");
        songIds.add("4");
        songIds.add("5");
        songIds.add("6");

        PlaylistDto expected = new PlaylistDto("1");

        PlaylistDto actual = playlistService.create(user.getId(),"MY_PLAYLIST_1" , songIds);
        Assertions.assertEquals(expected, actual);

        List<String> songIds2 = new ArrayList<>();
        songIds2.add("1");
        songIds2.add("3");
        songIds2.add("5");
        songIds2.add("6");
        PlaylistDto expected2 = new PlaylistDto("2");
        PlaylistDto actual2 = playlistService.create("1", "MY_PLAYLIST_2" , songIds2);
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    @DisplayName("Delete Playlist")
    void deletePlaylist() {

        // create
        List<String> songIds = new ArrayList<>();
        songIds.add("1");
        songIds.add("4");
        songIds.add("5");
        songIds.add("6");

        playlistService.create("1", "MY_PLAYLIST_1" , songIds);

        List<String> songIds2 = new ArrayList<>();
        songIds2.add("1");
        songIds2.add("3");
        songIds2.add("5");
        songIds2.add("6");
        playlistService.create("1", "MY_PLAYLIST_2" , songIds2);
        

        // delete
        String playlistId = "2";
        String userId = "1";
        playlistService.delete(userId, playlistId);
        Assertions.assertEquals("Delete Successful", "Delete Successful");
    }


    @Test
    @DisplayName("Delete Playlist when no such Playlist exists")
    void deletePlaylistPlaylistDoesNotExist() {
        String playlistId = "2";
        String userId = "1";
        Assertions.assertThrows(ResourceNotFoundException.class,()-> playlistService.delete(userId, playlistId));
    }


    @Test
    @DisplayName("Adding Songs to PlayList")
    void addSongsToPlaylist() {
        // add playlist
        List<String> songIds2 = new ArrayList<>();
        songIds2.add("1");
        songIds2.add("4");
        songIds2.add("5");
        songIds2.add("6");
        
        playlistService.create("1", "MY_PLAYLIST_1" , songIds2);

        // modify
        String playlistId = "1";
        String userId = "1";
        List<String> songIds = new ArrayList<>();
        songIds.add("7");
        songIds.add("5");
        songIds.add("1");

        List<String> expectedSongIds = new ArrayList<>();
        expectedSongIds.add("1");
        expectedSongIds.add("4");
        expectedSongIds.add("5");
        expectedSongIds.add("6");
        expectedSongIds.add("7");

        PlaylistModifyDto expected = new PlaylistModifyDto("1", "MY_PLAYLIST_1", expectedSongIds);
        PlaylistModifyDto actual = playlistService.addSong(userId, playlistId, songIds);

        Assertions.assertEquals(expected, actual);
    
    }


    @Test
    @DisplayName("Adding Songs to PlayList But Song Doesn't exist")
    void addSongsToPlaylistSongDoesNotExists() {
        // add playlist
        List<String> songIds2 = new ArrayList<>();
        songIds2.add("1");
        songIds2.add("4");
        songIds2.add("5");
        songIds2.add("6");
        
        playlistService.create("1", "MY_PLAYLIST_1" , songIds2);

        // modify
        String playlistId = "1";
        String userId = "1";
        List<String> songIds = new ArrayList<>();
        songIds.add("7");
        songIds.add("31");
        songIds.add("1");

        List<String> expectedSongIds = new ArrayList<>();
        expectedSongIds.add("1");
        expectedSongIds.add("4");
        expectedSongIds.add("5");
        expectedSongIds.add("6");
        expectedSongIds.add("7");

        // PlaylistModifyDto expected = new PlaylistModifyDto("1", "MY_PLAYLIST_1", expectedSongIds);
        // PlaylistModifyDto actual = playlistService.addSong(userId, playlistId, songIds);

        // Assertions.assertEquals(expected, actual);
    
        Assertions.assertThrows(ResourceNotFoundException.class, () -> playlistService.addSong(userId, playlistId, songIds));
    }


    @Test
    @DisplayName("Adding Songs, PlayList Doesn't exist")
    void addSongsPlaylistDoesNotExists() {

        

        // add playlist
        List<String> songIds2 = new ArrayList<>();
        songIds2.add("1");
        songIds2.add("4");
        songIds2.add("5");
        songIds2.add("6");
        
        playlistService.create("1", "MY_PLAYLIST_1" , songIds2);

        // modify
        String playlistId = "5";
        String userId = "1";
        List<String> songIds = new ArrayList<>();
        songIds.add("7");
        songIds.add("1");

        List<String> expectedSongIds = new ArrayList<>();
        expectedSongIds.add("1");
        expectedSongIds.add("4");
        expectedSongIds.add("5");
        expectedSongIds.add("6");
        expectedSongIds.add("7");
    
        Assertions.assertThrows(ResourceNotFoundException.class, () -> playlistService.addSong(userId, playlistId, songIds));
    }


    @Test
    @DisplayName("Removing Songs from PlayList")
    void deleteSongsFromPlaylist() {
        // add playlist
        List<String> songIds2 = new ArrayList<>();
        songIds2.add("1");
        songIds2.add("4");
        songIds2.add("5");
        songIds2.add("6");
        
        playlistService.create("1", "MY_PLAYLIST_1" , songIds2);

        // modify (delete)
        String playlistId = "1";
        String userId = "1";
        List<String> songIdsToRemove = new ArrayList<>();
        songIdsToRemove.add("5");
        songIdsToRemove.add("1");

        List<String> expectedSongIds = new ArrayList<>();
        expectedSongIds.add("4");
        expectedSongIds.add("6");

        PlaylistModifyDto expected = new PlaylistModifyDto("1", "MY_PLAYLIST_1", expectedSongIds);
        PlaylistModifyDto actual = playlistService.deleteSong(userId, playlistId, songIdsToRemove);

        Assertions.assertEquals(expected, actual);
    
    }


    @Test
    @DisplayName("Removing Songs to PlayList, Song doesn't present in Playlist")
    void deleteSongsFromPlaylistSongNotPresent() {
        // add playlist
        List<String> songIds2 = new ArrayList<>();
        songIds2.add("1");
        songIds2.add("4");
        songIds2.add("5");
        songIds2.add("6");
        
        playlistService.create("1", "MY_PLAYLIST_1" , songIds2);

        // modify (delete)
        String playlistId = "1";
        String userId = "1";
        List<String> songIds = new ArrayList<>();
        songIds.add("1");
        songIds.add("7");

        List<String> expectedSongIds = new ArrayList<>();
        expectedSongIds.add("4");
        expectedSongIds.add("6");

        Assertions.assertThrows(ResourceNotFoundException.class, () -> playlistService.deleteSong(userId, playlistId, songIds));
    }


    @Test
    @DisplayName("Removing Songs to PlayList, PlayList doesn't present")
    void deleteSongsFromPlaylistNotFound() {
        // add playlist
        List<String> songIds2 = new ArrayList<>();
        songIds2.add("1");
        songIds2.add("4");
        songIds2.add("5");
        songIds2.add("6");
        
        playlistService.create("1", "MY_PLAYLIST_1" , songIds2);

        // modify (delete)
        String playlistId = "4";
        String userId = "1";
        List<String> songIds = new ArrayList<>();
        songIds.add("1");
        songIds.add("7");

        List<String> expectedSongIds = new ArrayList<>();
        expectedSongIds.add("4");
        expectedSongIds.add("6");

        Assertions.assertThrows(ResourceNotFoundException.class, () -> playlistService.deleteSong(userId, playlistId, songIds));
    }

}
