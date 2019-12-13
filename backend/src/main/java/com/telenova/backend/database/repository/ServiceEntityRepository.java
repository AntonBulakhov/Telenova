package com.telenova.backend.database.repository;

import com.telenova.backend.database.entity.ServiceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceEntityRepository extends CrudRepository<ServiceEntity, Integer> {
    List<ServiceEntity> findAllByUserId(Integer id);
}
