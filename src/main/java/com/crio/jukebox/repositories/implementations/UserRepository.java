package com.crio.jukebox.repositories.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.ResourceNotFoundException;
import com.crio.jukebox.repositories.interfaces.IUserRepository;


public class UserRepository implements IUserRepository {

    private Map<String, User> userMap;
    int autoIncrement = 0;


    public UserRepository() {
        this.userMap = new HashMap<String, User>();
    }

    // public UserRepository(Map<String, User> userMap) {
    //     this.userMap = userMap;
    //     this.autoIncrement = userMap.size();
    // }

    @Override
    public User save(User entity) {
        if(entity.getId() == null) {
            autoIncrement++;
            User u = new User(Integer.toString(autoIncrement), entity.getName());
            userMap.put(u.getId(), u);
            return u;
        }
        userMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void deleteById(String id) {
        userMap.remove(id);
    }

    @Override
    public List<User> findAll() {
        if(userMap.isEmpty()) return new ArrayList<>();

        return userMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public User findById(String id) {
        if(!userMap.containsKey(id)) {
            throw new ResourceNotFoundException("User with userId "+ id +" Not Found!!!");
        }
        return userMap.get(id);
    }


    @Override
    public Playlist getUsersPlaylist(String userId, String playlistId) {
        User user = userMap.get(userId);
        for(Playlist playlist: user.getPlaylists()) {
            if(playlist.getId().equals(playlistId)) {
                return playlist;
            }
        }
        return null;
    }
    
}
