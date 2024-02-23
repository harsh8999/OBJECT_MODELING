package com.crio.jukebox.exceptions;

public class UserHavePlayListException extends RuntimeException {

    public UserHavePlayListException() {
        super();
    }

    public UserHavePlayListException(String message) {
        super(message);
    }
}