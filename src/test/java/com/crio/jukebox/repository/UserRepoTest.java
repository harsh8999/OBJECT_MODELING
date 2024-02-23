package com.crio.jukebox.repository;


import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.implementations.UserRepository;
import com.crio.jukebox.repositories.interfaces.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("User Repository Test")
public class UserRepoTest {
    IUserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository = new UserRepository();
    }

    @Test
    void CreateUserFromRepo() {
        
        User user1 = new User(null, "Harsh");
        User user2 = new User(null, "Hema");
        User user3 = new User(null, "Nikhil");
        User savedUser1 = userRepository.save(user1);
        User savedUser2 = userRepository.save(user2);
        User savedUser3 = userRepository.save(user3);
        System.out.println(savedUser1);
        System.out.println(savedUser2);
        System.out.println(savedUser3);
    }
}
