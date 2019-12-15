package com.telenova.backend.database.repository;

import com.telenova.backend.database.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleEntityRepository extends CrudRepository<RoleEntity, Integer> {
}
