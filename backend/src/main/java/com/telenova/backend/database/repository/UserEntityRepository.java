package com.telenova.backend.database.repository;

import com.telenova.backend.database.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity getByLogin(String login);
}
