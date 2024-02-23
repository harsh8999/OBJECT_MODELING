package com.crio.jukebox.service;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.dto.PlaylistDto;
import com.crio.jukebox.dto.SongDto;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.implementations.AblumRepository;
import com.crio.jukebox.repositories.implementations.ArtistRepository;
import com.crio.jukebox.repositories.implementations.PlayerRepository;
import com.crio.jukebox.repositories.implementations.PlaylistRepository;
import com.crio.jukebox.repositories.implementations.SongRepository;
import com.crio.jukebox.repositories.interfaces.IAlbumRepository;
import com.crio.jukebox.repositories.interfaces.IArtistRepository;
import com.crio.jukebox.repositories.interfaces.IPlayerRepository;
import com.crio.jukebox.repositories.interfaces.IPlaylistRepository;
import com.crio.jukebox.repositories.interfaces.ISongRepository;
import com.crio.jukebox.repositories.interfaces.IUserRepository;
import com.crio.jukebox.services.AlbumServiceImpl;
import com.crio.jukebox.services.ArtistServiceImpl;
import com.crio.jukebox.services.IAlbumService;
import com.crio.jukebox.services.IArtistService;
import com.crio.jukebox.services.IPlayerService;
import com.crio.jukebox.services.IPlaylistService;
import com.crio.jukebox.services.ISongService;
import com.crio.jukebox.services.PlayerServiceImpl;
import com.crio.jukebox.services.PlaylistServiceImpl;
import com.crio.jukebox.services.SongServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Player Service")
public class PlayerService {

    private IPlayerRepository playerRepository;
    private IPlayerService playerService;


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
    private PlaylistDto playlistDto;

    
    @BeforeEach
    void setup() {
        playlistRepository = new PlaylistRepository();
        songRepository = new SongRepository();
        userRepository = new com.crio.jukebox.repositories.implementations.UserRepository();
        songService = new SongServiceImpl(songRepository);
        playlistService = new PlaylistServiceImpl(playlistRepository, songRepository, userRepository);
        albumRepository = new AblumRepository();
        albumService = new AlbumServiceImpl(albumRepository);
        artistRepository = new ArtistRepository();
        artistService = new ArtistServiceImpl(artistRepository);
        playerRepository = new PlayerRepository();
        playerService = new PlayerServiceImpl(playerRepository, playlistRepository);

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
    
        List<String> s2 = new ArrayList<>();
        s2.add("1");
        s2.add("2");
        s2.add("3");
        s2.add("4");
        playlistDto = playlistService.create("1", "MY_PLAYLIST_1" , s2);
        SongDto songDto = playerService.playPlayList(user.getId(), playlistDto.getId());
        System.out.println(songDto);
    }

    @Test
    @DisplayName("Play Playlist")
    void playPlayList() {
        SongDto songDto = playerService.playPlayList(user.getId(), playlistDto.getId());
        System.out.println(songDto);
    }


    @Test
    @DisplayName("Play Next Song")
    void playNextSong() {
        
        SongDto songDtoNext = playerService.playNextSong();
        System.out.println(songDtoNext);
        SongDto songDtoNext1 = playerService.playNextSong();
        System.out.println(songDtoNext1);
        SongDto songDtoNext2 = playerService.playNextSong();
        System.out.println(songDtoNext2);
        SongDto songDtoNext3 = playerService.playNextSong();
        System.out.println(songDtoNext3);
        SongDto songDtoNext4 = playerService.playNextSong();
        System.out.println(songDtoNext4);
    }


    @Test
    @DisplayName("Play Previous Song")
    void playpreviousSong() {
        
        
        SongDto songDtoNext = playerService.playPrevSong();
        System.out.println(songDtoNext);
        SongDto songDtoNext1 = playerService.playPrevSong();
        System.out.println(songDtoNext1);
        SongDto songDtoNext2 = playerService.playPrevSong();
        System.out.println(songDtoNext2);
        SongDto songDtoNext3 = playerService.playPrevSong();
        System.out.println(songDtoNext3);
        SongDto songDtoNext4 = playerService.playPrevSong();
        System.out.println(songDtoNext4);
    }


    @Test
    @DisplayName("Play Song")
    void playSong() {        
        SongDto songDtoNext = playerService.playSong("3");
        System.out.println(songDtoNext);
        SongDto songDtoNext6 = playerService.playNextSong();
        System.out.println(songDtoNext6);
        SongDto songDtoNext1 = playerService.playPrevSong();
        System.out.println(songDtoNext1);
        SongDto songDtoNext4 = playerService.playNextSong();
        System.out.println(songDtoNext4);
    }
}
