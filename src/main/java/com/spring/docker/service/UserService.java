
package com.spring.docker.service;

import com.spring.docker.exception.APIResponse;
import com.spring.docker.payload.UserDTO;
import com.spring.docker.payload.UserRequestDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO createUser(UserRequestDTO userRequestDto);

    APIResponse deleteUser(Long userId);

    UserDTO updateUser(UserRequestDTO userRequestDto, Long userId);
}
