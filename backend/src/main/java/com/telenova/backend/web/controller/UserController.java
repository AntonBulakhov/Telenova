package com.telenova.backend.web.controller;

import com.telenova.backend.database.entity.RoleEntity;
import com.telenova.backend.database.entity.UserEntity;
import com.telenova.backend.database.entity.UserStatusEntity;
import com.telenova.backend.service.UserService;
import com.telenova.backend.web.dto.SafeUser;
import com.telenova.backend.web.dto.UserWithSumDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @GetMapping("/status/all")
    public List<UserStatusEntity> getAllStatuses() {
        return userService.getAllStatuses();
    }

    @GetMapping("/role/all")
    public List<RoleEntity> getAllRoles() {
        return userService.getAllRoles();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Boolean saveUser(@RequestBody UserEntity userEntity) {
        return userService.saveUser(userEntity);
    }

    @PreAuthorize("hasAnyRole('SUPER', 'ADMIN')")
    @GetMapping("/role/{id}")
    public List<UserWithSumDto> getAllUsersByRoleId(@PathVariable Integer id) {
        return userService.getAllUserByRoleId(id);
    }

    @PreAuthorize("hasAnyRole('SUPER', 'ADMIN')")
    @PostMapping("/status")
    public Boolean setUserStatus(@RequestBody UserEntity userEntity) {
        return userService.setUserStatus(userEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
