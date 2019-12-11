package com.telenova.backend.database.repository;

import com.telenova.backend.database.entity.OfferingTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferingTypeEntityRepository extends CrudRepository<OfferingTypeEntity, Integer> {

}
