package com.telenova.backend.database.repository;

import com.telenova.backend.database.entity.UserStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusEntityRepository extends CrudRepository<UserStatusEntity, Integer> {
}
