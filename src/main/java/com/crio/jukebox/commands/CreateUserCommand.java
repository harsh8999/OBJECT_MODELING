package com.crio.jukebox.commands;

import java.util.List;

import com.crio.jukebox.dto.UserDto;
import com.crio.jukebox.services.IUserService;

public class CreateUserCommand implements ICommand {
    private IUserService userService;

    public CreateUserCommand(IUserService userService) {
        this.userService = userService;
    }

    // Execute create method of IUserService and print the result.
    // Sample Input Token List:- ["CREATE-USER","Harsh"]
    @Override
    public void execute(List<String> tokens) {
        String name = tokens.get(1);
        UserDto userDto = userService.createUser(name);
        System.out.println(userDto);
    }

    
}
