package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.crio.jukebox.dataloader.SongDataLoaderFactory;
import com.crio.jukebox.dataloader.DataLoader;
import com.crio.jukebox.dataloader.DataLoaderFactory;
import com.crio.jukebox.dataloader.enums.FileType;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.services.IAlbumService;
import com.crio.jukebox.services.IArtistService;
import com.crio.jukebox.services.ISongService;

public class LoadDataCommand implements ICommand {
    
    private ISongService songService;
    private IAlbumService albumService;
    private IArtistService artistService;
    private DataLoaderFactory<Song> dataLoaderFactory;

    public LoadDataCommand(ISongService songService, IAlbumService albumService, IArtistService artistService) {
        this.albumService = albumService;
        this.songService = songService;
        this.artistService = artistService;
    }

    // Sample Input Token List:- ["LOAD-DATA","songs.csv"]
    @Override
    public void execute(List<String> tokens) {
        dataLoaderFactory = new SongDataLoaderFactory();
        DataLoader<Song> dataLoader = dataLoaderFactory.getDataLoader(FileType.CSV);
        List<Song> songs = dataLoader.load(tokens.get(1));


        // create album artist
        for(Song song: songs) {
            Artist createAlbumArtist = artistService.create(song.getAlbum().getArtist().getName());
            albumService.create(song.getAlbum().getTitle(), createAlbumArtist, null); 
        }
        
        
        for(Song song: songs) {
            Set<Artist> createdArtistsSet = new HashSet<>();
            List<Artist> createdArtists = new ArrayList<>();
            for(Artist artist: song.getArtists()) {
                Artist createdArtist = artistService.create(artist.getName());
                if(!createdArtistsSet.contains(createdArtist)) {
                    createdArtists.add(createdArtist);
                }
            }
            song.setArtists(createdArtists);
        }

        

        // create song
        songs.forEach(song -> songService.createSong(null, song.getTitle(), song.getGenre(), song.getAlbum(), song.getArtists()));

        System.out.println("Songs Loaded successfully");
    }

}
