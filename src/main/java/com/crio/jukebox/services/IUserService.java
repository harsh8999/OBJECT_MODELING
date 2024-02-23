package com.crio.jukebox.services;

import com.crio.jukebox.dto.UserDto;

public interface IUserService {
    UserDto createUser(String name); 
}