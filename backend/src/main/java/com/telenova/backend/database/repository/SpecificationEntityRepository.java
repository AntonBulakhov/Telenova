package com.telenova.backend.database.repository;

import com.telenova.backend.database.entity.SpecificationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationEntityRepository extends CrudRepository<SpecificationEntity, Integer> {
    SpecificationEntity findByName(String name);
}
