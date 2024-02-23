package com.crio.jukebox.service;

import com.crio.jukebox.dto.UserDto;
import com.crio.jukebox.repositories.implementations.UserRepository;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("User Service Test")
public class UserServiceTest {
    IUserService userService;

    @BeforeEach
    public void setup() {
        userService = new UserServiceImpl(new UserRepository());
    }

    @Test
    void CreateUserFromRepo() {
                
        UserDto expUserDto1 = new UserDto("1", "Harsh");
        UserDto expUserDto2 = new UserDto("2", "Hema");

        UserDto savedUser1 = userService.createUser("Harsh");
        UserDto savedUser2 = userService.createUser("Hema");

        Assertions.assertEquals(expUserDto1, savedUser1);
        Assertions.assertEquals(expUserDto2, savedUser2);
        
    }
}
