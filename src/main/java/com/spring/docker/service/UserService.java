package com.spring.docker.service;

import com.spring.docker.payload.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
}
