package com.stream.platform.exception;

public class ProgramTypeNotFoundException extends RuntimeException{
    public ProgramTypeNotFoundException(String message){
        super(message);
    }
}
