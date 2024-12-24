package com.pointscontainer.errors;

public class PointNotFoundException extends RuntimeException {
    public PointNotFoundException(String message) {
        super(message);
    }
}
