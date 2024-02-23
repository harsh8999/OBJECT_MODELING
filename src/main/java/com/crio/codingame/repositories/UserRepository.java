package com.crio.codingame.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crio.codingame.entities.User;

public class UserRepository implements IUserRepository{

    private final Map<String,User> userMap;
    private Integer autoIncrement = 0;

    public UserRepository(){
        userMap = new HashMap<String,User>();
    }

    public UserRepository(Map<String, User> userMap) {
        this.userMap = userMap;
        this.autoIncrement = userMap.size();
    }

    @Override
    public User save(User entity) {
        if( entity.getId() == null ){
            autoIncrement++;
            User u = new User(Integer.toString(autoIncrement),entity.getName(),entity.getScore());
            userMap.put(u.getId(),u);
            return u;
        }
        userMap.put(entity.getId(),entity);
        return entity;
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find all the list of User Present in the Repository
    // Tip:- Use Java Streams

    @Override
    public List<User> findAll() {
        return userMap.entrySet().stream()
                                    .map(e -> e.getValue())
                                    .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return userMap.containsKey(id);
    }

    @Override
    public void delete(User entity) {
        for(Map.Entry<String, User> entry: userMap.entrySet()) {
            if(entry.getValue().getId() == entity.getId()) {
                userMap.remove(entry.getKey());
                break;
            }
        }
    }

    @Override
    public void deleteById(String id) {
        userMap.remove(id);
    }

    @Override
    public long count() {
        return userMap.size();
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find the User Present in the Repository provided name
    // Tip:- Use Java Streams

    @Override
    public Optional<User> findByName(String name) {
        return userMap.entrySet().stream()
                                    .map(e -> e.getValue())
                                    .filter(e -> e.getName().equals(name))
                                    .findFirst();
    }
    
}
