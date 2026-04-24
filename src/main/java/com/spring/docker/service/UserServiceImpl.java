
package com.spring.docker.service;

import com.spring.docker.payload.UserDTO;
import com.spring.docker.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<UserDTO> getAllUsers() {

        return userRepository
                .findAll()
                .stream()
                .map(item -> modelMapper.map(item, UserDTO.class))
                .toList();
    }
}
