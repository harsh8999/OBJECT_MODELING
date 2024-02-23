package com.crio.jukebox.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import com.crio.jukebox.dto.SongDto;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.repositories.implementations.AblumRepository;
import com.crio.jukebox.repositories.implementations.ArtistRepository;
import com.crio.jukebox.repositories.implementations.SongRepository;
import com.crio.jukebox.repositories.interfaces.IAlbumRepository;
import com.crio.jukebox.repositories.interfaces.IArtistRepository;
import com.crio.jukebox.repositories.interfaces.ISongRepository;
import com.crio.jukebox.services.AlbumServiceImpl;
import com.crio.jukebox.services.ArtistServiceImpl;
import com.crio.jukebox.services.IAlbumService;
import com.crio.jukebox.services.IArtistService;
import com.crio.jukebox.services.ISongService;
import com.crio.jukebox.services.SongServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Song Service Test")
public class SongServiceTest {
    private ISongService songService;
    private ISongRepository songRepository;
    private IAlbumRepository albumRepository;
    private IAlbumService albumService;
    private IArtistRepository artistRepository;
    private IArtistService artistService;

    @BeforeEach
    public void setup() {
        songRepository = new SongRepository();
        songService = new SongServiceImpl(songRepository);
        albumRepository = new AblumRepository();
        albumService = new AlbumServiceImpl(albumRepository);
        artistRepository = new ArtistRepository();
        artistService = new ArtistServiceImpl(artistRepository);

    }

    // @Test
    // @DisplayName("Add Song")
    // void addSong() {
        
    //     // 1,South of the Border,Pop,No.6 Collaborations Project,Ed Sheeran,Ed Sheeran#Cardi.B#Camilla Cabello
    //     String id = "1";
    //     String title = "South of the Border";
    //     String genre = "Pop";
    //     String album = "No.6 Collaborations Project";
    //     String albumArtist = "Ed Sheeran";
    //     List<String> artists = new ArrayList<>();
    //     artists.add("Ed Sheeran");
    //     artists.add("Cardi.B");
    //     artists.add("Camilla Cabello");


    //     SongDto expected = new SongDto(title, album, artists);
    //     SongDto actual = songService.createSong(id, title, genre, album, albumArtist, artists);
        
    //     Assertions.assertEquals(expected, actual);
    // }

    // @Test
    // @DisplayName("Add Multiple Songs")
    // void addSongs() {
    //     String file = "1,South of the Border,Pop,No.6 Collaborations Project,Ed Sheeran,Cardi.B#Ed Sheeran#Camilla Cabello\n"+
    //                     "2,I Dont'Care,Pop,No.6 Collaborations Project,Ed Sheeran,Justin Bieber\n"+
    //                     "3,Remember The Name,Pop,No.6 Collaborations Project,Ed Sheeran,Ed Sheeran#Eminem#50Cent\n"+
    //                     "4,Way To Break My Heart,Pop,No.6 Collaborations Project,Ed Sheeran,Ed Sheeran#Skrillex\n"+
    //                     "5,Cross Me,Pop,No.6 Collaborations Project,Ed Sheeran,Ed Sheeran#Chance The Rapper#PnB Rock\n"+
    //                     "6,Give Life Back To Music,Electronic Dance Music,Random Access Memories,Daft Punk,Daft Punk#Nile Rodgers\n"+
    //                     "7,Instant Crush,Electronic Dance Music,Random Access Memories,Daft Punk,Daft Punk#Julian Casablancas\n"+
    //                     "8,Get Lucky,Electronic Dance Music,Random Access Memories,Daft Punk,Daft Punk#Nile Rodgers#Pharrell Williams\n"+
    //                     "9,Lose Yourself To Dance,Electronic Dance Music,Random Access Memories,Daft Punk,Daft Punk#Nile Rodgers#Pharrell William\n"+
    //                     "10,Giorgio by Moroder,Electronic Dance Music,Random Access Memories,Daft Punk,Daft Punk#Giorgio Moroder\n"+
    //                     "11,Something In the Way,Rock,Nevermind,Nirvana,Nirvana\n"+
    //                     "12,Lithium,Rock,Nevermind,Nirvana,Nirvana\n"+
    //                     "13,Come as You Are,Rock,Nevermind,Nirvana,Nirvana\n"+
    //                     "14,Stay Away,Rock,Nevermind,Nirvana,Nirvana\n"+
    //                     "15,Lounge Act,Rock,Nevermind,Nirvana,Nirvana\n"+
    //                     "16,BLOOD.,Hip-Hop,DAMN.,Kendrick Lamar,Kendrick Lamar\n"+
    //                     "17,GOD.,Hip-Hop,DAMN.,Kendrick Lamar,Kendrick Lamar\n"+
    //                     "18,PRIDE.,Hip-Hop,DAMN.,Kendrick Lamar,Kendrick Lamar\n"+
    //                     "19,YAH.,Hip-Hop,DAMN.,Kendrick Lamar,Kendrick Lamar\n"+
    //                     "20,DUCKWORTH.,Hip-Hop,DAMN.,Kendrick Lamar,Kendrick Lamar\n"+
    //                     "21,Flamenco Sketches,Jazz,Kind of Blue,Miles Davis,Miles Davis\n"+
    //                     "22,So What,Jazz,Kind of Blue,Miles Davis,Miles Davis\n"+
    //                     "23,Freddie Freeloader,Jazz,Kind of Blue,Miles Davis,Miles Davis\n"+
    //                     "24,Blue in Green,Jazz,Kind of Blue,Miles Davis,Miles Davis\n"+
    //                     "25,All Blues,Jazz,Kind of Blue,Miles Davis,Miles Davis\n"+
    //                     "26,The Show Must Go on,Rock,Innuendo,Queen,Queen\n"+
    //                     "27,Somebody To Love,Rock,A Day at the Races,Queen,Queen\n"+
    //                     "28,Killer Queen,Rock,Sheer Heart Attack,Queen,Queen\n"+
    //                     "29,Another One Bites the Dust,Rock,The Game,Queen,Queen\n"+
    //                     "30,Bohemian Rhapsody,Rock,A Night at the Opera,Queen,Queen";
    //     String[] songs = file.split("\n");

    //     for(String song : songs) {
    //         String[] parts = song.split(",");
            
    //         String id = parts[0];
    //         String title = parts[1];
    //         String genre = parts[2];
    //         String album = parts[3];
    //         String albumArtist = parts[4];
    //         String[] artistsArray = parts[5].split("#");
    //         List<String> artists = new ArrayList<>();
    //         for(String artist: artistsArray) {
    //             artists.add(artist);
    //         }
            
    //         SongDto expected = new SongDto(title, album, artists);
    //         SongDto actual = songService.createSong(id, title, genre, album, albumArtist, artists);
        
    //         Assertions.assertEquals(expected, actual);
    //     }
    // }


    @Test
    @DisplayName("Read songs.csv and add songs")
    void addTestFromFile() {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("songs.csv"));) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        // System.out.println(records);

        for (List<String> record: records) {
            
            
            String title = record.get(0);
            String genre = record.get(1);
            String albumTitle = record.get(2);
            String albumArtistName = record.get(3);
            Artist albumArtist = artistService.create(albumArtistName);
            // create album
            Album album = albumService.create(albumTitle, albumArtist, null);
            
            String[] artistsArray = record.get(4).split("#");
            List<Artist> artists = new ArrayList<>();
            
            for(String artistName: artistsArray) {
                artists.add(artistService.create(artistName));
            }

            List<String> artistsId = artists.stream().map(e -> e.getName()).collect(Collectors.toList());

            SongDto expected = new SongDto(title, album.getTitle(), artistsId);
            SongDto actual = songService.createSong(null, title, genre, album, artists);
        
            Assertions.assertEquals(expected, actual);
            
        }

        // System.out.println(albumRepository.findAll());

        Assertions.assertEquals(30,songRepository.findAll().size());
    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }
}
