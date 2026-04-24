
package com.spring.docker.service;

import com.spring.docker.exceptions.APIResponse;
import com.spring.docker.model.User;
import com.spring.docker.payload.UserDTO;
import com.spring.docker.payload.UserRequestDTO;
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

    @Override
    public UserDTO createUser(UserRequestDTO userRequestDto) {
        try {
            User user = modelMapper.map(userRequestDto, User.class);
            user = userRepository.save(user);
            return modelMapper.map(user, UserDTO.class);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error during creation");
        }
    }

    @Override
    public APIResponse deleteUser(Long userId) {
        try {
            userRepository.deleteById(userId);
            return new APIResponse("deleted");
        } catch (RuntimeException e) {
            throw new RuntimeException("Error during deletion");
        }
    }

    @Override
    public UserDTO updateUser(UserRequestDTO userRequestDto, Long userId) {
        try {
            User user = modelMapper.map(userRequestDto, User.class);
            user.setUserId(userId);
            user = userRepository.save(user);
            return modelMapper.map(user, UserDTO.class);

        } catch (RuntimeException e) {
            throw new RuntimeException("Error during updation");
        }
    }

}
