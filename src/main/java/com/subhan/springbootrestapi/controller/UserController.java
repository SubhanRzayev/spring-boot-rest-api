package com.subhan.springbootrestapi.controller;

import com.subhan.springbootrestapi.dto.UserDto;
import com.subhan.springbootrestapi.entity.User;
import com.subhan.springbootrestapi.service.UserService;
import com.subhan.springbootrestapi.util.CustomPage;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    // users
    @GetMapping
    public List<UserDto> getUser() {
        return userService.getUsers();
    }

    //user/id
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    //user/create

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto userCreate = userService.createUser(userDto);
        return ResponseEntity.ok(userCreate);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        UserDto userUpdate = userService.updateUser(id,userDto);
        return ResponseEntity.ok(userUpdate);
    }


    // Pagination
    @GetMapping("/pagination")
    public ResponseEntity<Page<User>> pagination(@RequestParam int currentPage, @RequestParam int pageSize) {
        return ResponseEntity.ok(userService.pagination(currentPage,pageSize));
    }

    @GetMapping("/pagination/v1")
    public ResponseEntity<Page<User>> paginationV1(Pageable pageable) {
        return ResponseEntity.ok(userService.pagination(pageable));
    }

    //bu daha performanslidi
    @GetMapping("/pagination/v2")
    public ResponseEntity<Slice<User>> paginationV2(Pageable pageable) {
        return ResponseEntity.ok(userService.slice(pageable));
    }

    //Custom Pagination

    @GetMapping("/pagination/v3")
    public ResponseEntity<CustomPage<UserDto>> customPagePagination(Pageable pageable) {
        return ResponseEntity.ok(userService.customPagePagination(pageable));

    }



    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeUser(@PathVariable Long id) {

        String message = userService.deleteUser(id);
        return ResponseEntity.ok(message);
    }




}
