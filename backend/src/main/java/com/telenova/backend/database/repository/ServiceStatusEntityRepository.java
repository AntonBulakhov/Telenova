package com.telenova.backend.database.repository;

import com.telenova.backend.database.entity.ServiceStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceStatusEntityRepository extends CrudRepository<ServiceStatusEntity, Integer> {

}
