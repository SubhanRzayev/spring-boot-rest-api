package com.subhan.springbootrestapi.service.impl;

import com.subhan.springbootrestapi.dto.UserDto;
import com.subhan.springbootrestapi.entity.User;
import com.subhan.springbootrestapi.exception.ResourceNotFoundException;
import com.subhan.springbootrestapi.repository.UserRepository;
import com.subhan.springbootrestapi.service.UserService;
import com.subhan.springbootrestapi.util.CustomPage;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return  users.stream()
                .map(user -> modelMapper.map(user,UserDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public UserDto getUserById(Long id) {
        User userId = userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Client not exist with id:" + id));

        return modelMapper.map(userId,UserDto.class);

    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser,UserDto.class);

    }

    @Override
    public UserDto updateUser(Long id,UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);

        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not exist with id:" + id));

        updateUser.setFirstname(user.getFirstname());
        updateUser.setLastname(user.getLastname());
        updateUser.setEmail(user.getEmail());
        return modelMapper.map(updateUser,UserDto.class);
    }

    @Override
    public String deleteUser(Long id) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Client not exist with id:" + id));
        userRepository.delete(user);
        return "User with id " + id + " has been deleted.";

    }

    @Override
    public Page<User> pagination(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> pagination(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Slice<User> slice(Pageable pageable) {
        return userRepository.findAll(pageable);

    }

    @Override
    public CustomPage<UserDto> customPagePagination(Pageable pageable) {
        Page<User> data = userRepository.findAll(pageable);
        UserDto[] dtos = modelMapper.map(data.getContent(), UserDto[].class);

        return new CustomPage<UserDto>(data, Arrays.asList(dtos));
    }
}
