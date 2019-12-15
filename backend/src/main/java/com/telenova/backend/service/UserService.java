package com.telenova.backend.service;

import com.telenova.backend.database.entity.UserEntity;
import com.telenova.backend.web.dto.SafeUser;

public interface UserService {
    Boolean saveUser(UserEntity userEntity);
    UserEntity getUserByLogin(String login);
    SafeUser getSafeUserByLogin(String login);
    UserEntity getUserById(Integer id);
}
