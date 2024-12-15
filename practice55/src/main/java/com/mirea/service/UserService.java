package com.mirea.service;

import com.mirea.controller.UserRequest;
import com.mirea.entity.UserEntity;

public interface UserService {

    UserEntity getUser(int id);
    UserEntity addUser(UserRequest userRequest);
}