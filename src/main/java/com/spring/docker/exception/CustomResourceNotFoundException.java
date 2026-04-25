package com.spring.docker.exception;


public class CustomResourceNotFoundException extends RuntimeException {
    String entity;
    Long id;
    public CustomResourceNotFoundException(String entity, Long id) {
        super("Resource " + entity + " with Id " + id + " Not Found");
    }
}
