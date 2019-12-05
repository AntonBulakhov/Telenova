package com.telenova.backend.database.repository;

import com.telenova.backend.database.entity.OfferingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferingEntityRepository extends CrudRepository<OfferingEntity, Integer> {
}
