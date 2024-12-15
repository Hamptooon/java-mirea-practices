package com.mirea.controller;

import com.mirea.entity.UserEntity;
import com.mirea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping
//    public ResponseEntity<UserEntity> getUserById(@RequestParam int id) {
//        UserEntity user = userService.getUser(id);
//        return ResponseEntity.ok(user);
//    }
    @GetMapping
    public ResponseEntity<UserEntity> getUserById(
            @RequestParam(required = false) Integer id,
            @RequestBody(required = false) UserRequest userRequest) {

        int userId = (id != null) ? id : (userRequest != null ? userRequest.getId() : -1);
        if (userId == -1) {
            return ResponseEntity.badRequest().body(null); // Возвращаем ошибку, если id не передан
        }

        UserEntity user = userService.getUser(userId);

        return ResponseEntity.ok(user);
    }


    @PostMapping
    public ResponseEntity<UserEntity> getUserByJson(@RequestBody UserRequest userRequest) {
        UserEntity user = userService.getUser(userRequest.getId());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/add")
    public ResponseEntity<UserEntity> addUser(@RequestBody UserRequest userRequest) {
        UserEntity user = userService.addUser(userRequest);
        return ResponseEntity.ok(user);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleConflict(IllegalArgumentException e) {
        return e.getMessage();
    }
}