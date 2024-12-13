package com.pointscontainer.errors;

public class EmptyContainerException extends RuntimeException{
    public EmptyContainerException(String message){
        super(message);
    }
}
