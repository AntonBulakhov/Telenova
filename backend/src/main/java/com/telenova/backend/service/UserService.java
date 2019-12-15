package com.telenova.backend.service;

import com.telenova.backend.database.entity.RoleEntity;
import com.telenova.backend.database.entity.UserEntity;
import com.telenova.backend.database.entity.UserStatusEntity;
import com.telenova.backend.web.dto.SafeUser;
import com.telenova.backend.web.dto.UserWithSumDto;

import java.util.List;

public interface UserService {
    Boolean saveUser(UserEntity userEntity);

    UserEntity getUserByLogin(String login);

    SafeUser getSafeUserByLogin(String login);

    UserEntity getUserById(Integer id);

    List<UserStatusEntity> getAllStatuses();

    List<RoleEntity> getAllRoles();

    List<UserWithSumDto> getAllUserByRoleId(Integer id);

    Boolean setUserStatus(UserEntity userEntity);

    void deleteUser(Integer id);
}
