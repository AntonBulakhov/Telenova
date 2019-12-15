package com.telenova.backend.database.repository;

import com.telenova.backend.database.entity.RoleEntity;
import com.telenova.backend.database.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity getByLogin(String login);

    List<UserEntity> getAllByLoginOrEmail(String login, String email);

    List<UserEntity> getAllByRole(RoleEntity role);
}
