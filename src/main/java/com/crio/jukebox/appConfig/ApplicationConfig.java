package com.crio.jukebox.appConfig;

import com.crio.jukebox.services.AlbumServiceImpl;
import com.crio.jukebox.services.ArtistServiceImpl;
import com.crio.jukebox.services.IAlbumService;
import com.crio.jukebox.services.IArtistService;
import com.crio.jukebox.services.IPlayerService;
import com.crio.jukebox.services.IPlaylistService;
import com.crio.jukebox.services.ISongService;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.PlayerServiceImpl;
import com.crio.jukebox.services.PlaylistServiceImpl;
import com.crio.jukebox.services.SongServiceImpl;
import com.crio.jukebox.services.UserServiceImpl;
import com.crio.jukebox.commands.LoadDataCommand;
import com.crio.jukebox.commands.ModifyPlaylistCommand;
import com.crio.jukebox.commands.PlayPlaylistCommand;
import com.crio.jukebox.commands.PlaySongCommand;
import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.CreatePlaylistCommand;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.DeletePlaylistCommand;
import com.crio.jukebox.repositories.implementations.AblumRepository;
import com.crio.jukebox.repositories.implementations.ArtistRepository;
import com.crio.jukebox.repositories.implementations.PlayerRepository;
import com.crio.jukebox.repositories.implementations.PlaylistRepository;
import com.crio.jukebox.repositories.implementations.SongRepository;
import com.crio.jukebox.repositories.implementations.UserRepository;
import com.crio.jukebox.repositories.interfaces.IAlbumRepository;
import com.crio.jukebox.repositories.interfaces.IArtistRepository;
import com.crio.jukebox.repositories.interfaces.IPlayerRepository;
import com.crio.jukebox.repositories.interfaces.IPlaylistRepository;
import com.crio.jukebox.repositories.interfaces.ISongRepository;
import com.crio.jukebox.repositories.interfaces.IUserRepository;

public class ApplicationConfig {
    private final IUserRepository userRepository = new UserRepository();
    private final ISongRepository songRepository = new SongRepository();
    private final IPlaylistRepository playlistRepository = new PlaylistRepository();
    private final IAlbumRepository albumRepository = new AblumRepository();
    private final IArtistRepository artistRepository = new ArtistRepository();
    private final IPlayerRepository playerRepository = new PlayerRepository();

    private final IUserService userService = new UserServiceImpl(userRepository);
    private final ISongService songService = new SongServiceImpl(songRepository);
    private final IPlaylistService playlistService = new PlaylistServiceImpl(playlistRepository, songRepository, userRepository);
    private final IAlbumService albumService = new AlbumServiceImpl(albumRepository);
    private final IArtistService artistService = new ArtistServiceImpl(artistRepository);
    private final IPlayerService playerService = new PlayerServiceImpl(playerRepository, playlistRepository);

    private final CreateUserCommand createUserCommand = new CreateUserCommand(userService);
    private final LoadDataCommand loadDataCommand = new LoadDataCommand(songService, albumService, artistService);
    private final CreatePlaylistCommand createPlaylistCommand = new CreatePlaylistCommand(playlistService);
    private final DeletePlaylistCommand DeletePlaylistCommand = new DeletePlaylistCommand(playlistService);
    private final ModifyPlaylistCommand modifyPlaylistCommand = new ModifyPlaylistCommand(playlistService);
    private final PlayPlaylistCommand playPlaylistCommand = new PlayPlaylistCommand(playerService);
    private final PlaySongCommand playSongCommand = new PlaySongCommand(playerService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker() {
        commandInvoker.register("LOAD-DATA", loadDataCommand);
        commandInvoker.register("CREATE-USER", createUserCommand);
        commandInvoker.register("CREATE-PLAYLIST", createPlaylistCommand); 
        commandInvoker.register("DELETE-PLAYLIST", DeletePlaylistCommand); 
        commandInvoker.register("MODIFY-PLAYLIST", modifyPlaylistCommand); 
        commandInvoker.register("PLAY-PLAYLIST", playPlaylistCommand);
        commandInvoker.register("PLAY-SONG", playSongCommand);
        return commandInvoker;
    }

}
