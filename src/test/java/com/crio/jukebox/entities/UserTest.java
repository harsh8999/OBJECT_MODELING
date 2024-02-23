package com.crio.jukebox.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("User Test")
public class UserTest {

    @Test
    @DisplayName("Create User")
    void createUserTest() {
        String id = "1";
        String name = "Harsh";
        User user = new User(id, name);
        System.out.println(user);
    }

}
