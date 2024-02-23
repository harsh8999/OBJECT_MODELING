package com.crio.jukebox.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crio.jukebox.exceptions.NoSuchCommandException;

public class CommandInvoker {

    private static final Map<String, ICommand> commandMap = new HashMap<>();


    // Register Command
    public void register(String commandName, ICommand command) {
        commandMap.put(commandName, command);
    }

    // get the command
    public ICommand get(String commandName) {
        return commandMap.get(commandName);
    }

    public void executeCommand(String commandName, List<String> tokens) throws NoSuchCommandException {
        ICommand command = get(commandName);
        if(command == null){
            // Handle Exception
            throw new NoSuchCommandException();
        }
        command.execute(tokens);
    }
    
}
