package com.mohit.freesplitwise.CustomException;

public class GroupNotFoundException extends RuntimeException{
    public GroupNotFoundException(String message){
        super(message);
    }
}
