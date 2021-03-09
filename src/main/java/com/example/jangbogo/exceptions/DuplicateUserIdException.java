package com.example.jangbogo.exceptions;

public class DuplicateUserIdException extends RuntimeException{
    public DuplicateUserIdException(Throwable cause) {
        super(cause);
        System.out.println("@@DuplicateUserIdException!!!!");
    }
}
