package com.subhan.springbootrestapi.service;

import com.subhan.springbootrestapi.dto.UserDto;
import com.subhan.springbootrestapi.entity.User;
import com.subhan.springbootrestapi.util.CustomPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface UserService {
    public List<UserDto> getUsers();

    public UserDto getUserById(Long id);

    public UserDto createUser(UserDto userDto);

    public UserDto updateUser(Long id,UserDto userDto);

    public String deleteUser(Long id);

    Page<User> pagination(int currentPage, int pageSize);

    Page<User> pagination(Pageable pageable);

    Slice<User> slice(Pageable pageable);

    CustomPage<UserDto> customPagePagination(Pageable pageable);
}
