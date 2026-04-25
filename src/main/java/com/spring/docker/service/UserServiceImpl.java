
package com.spring.docker.service;

import com.spring.docker.exception.APIResponse;
import com.spring.docker.exception.CustomResourceNotFoundException;
import com.spring.docker.exception.CustomRuntimeException;
import com.spring.docker.model.User;
import com.spring.docker.payload.UserDTO;
import com.spring.docker.payload.UserRequestDTO;
import com.spring.docker.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<UserDTO> getAllUsers() {
        try {
            return userRepository
                    .findAll()
                    .stream()
                    .map(item -> modelMapper.map(item, UserDTO.class))
                    .toList();
        } catch (RuntimeException e) {
            throw new CustomRuntimeException(e.getMessage());
        }
    }

    @Override
    public UserDTO createUser(UserRequestDTO userRequestDto) {
        try {
            User user = userRepository.findByEmail(userRequestDto.getEmail());
            if(user != null) throw new CustomRuntimeException("User with email " + userRequestDto.getEmail() + " already exists");
            user = userRepository.findByUsername(userRequestDto.getUsername());
            if(user != null) throw new CustomRuntimeException("User with username " + userRequestDto.getUsername() + " already exists");
            user = modelMapper.map(userRequestDto, User.class);

            return modelMapper.map(userRepository.save(user), UserDTO.class);
        } catch (RuntimeException e) {
            throw new CustomRuntimeException(e.getMessage());
        }
    }

    @Override
    public APIResponse deleteUser(Long userId) {
        try {
            userRepository.findById(userId)
                    .orElseThrow(() -> new CustomResourceNotFoundException("User", userId));

            userRepository.deleteById(userId);

            return new APIResponse("deleted");
        } catch (RuntimeException e) {
            throw new CustomRuntimeException(e.getMessage());
        }
    }

    @Override
    public UserDTO updateUser(UserRequestDTO userRequestDto, Long userId) {
        try {
            userRepository.findById(userId)
                    .orElseThrow(() -> new CustomResourceNotFoundException("User", userId));

            User user = modelMapper.map(userRequestDto, User.class);
            user.setUserId(userId);

            return modelMapper.map(userRepository.save(user), UserDTO.class);
        } catch (RuntimeException e) {
            throw new CustomRuntimeException(e.getMessage());
        }
    }

}
