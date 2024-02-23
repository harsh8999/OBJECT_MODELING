package com.crio.jukebox.services;


import com.crio.jukebox.dto.UserDto;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.interfaces.IUserRepository;

public class UserServiceImpl implements IUserService{

    private IUserRepository userRepository;
    


    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(String name) {
        User user = new User(null, name);
        User savedUser = this.userRepository.save(user);
        UserDto userDto = new UserDto(savedUser.getId(), savedUser.getName());
        return userDto;
    }

    
    
}
