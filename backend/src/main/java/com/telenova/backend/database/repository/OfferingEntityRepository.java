package com.telenova.backend.database.repository;

import com.telenova.backend.database.entity.OfferingEntity;
import com.telenova.backend.database.entity.OfferingTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferingEntityRepository extends CrudRepository<OfferingEntity, Integer> {
    List<OfferingEntity> getAllByValueAndOfferingType(String value, OfferingTypeEntity offeringType);
    List<OfferingEntity> getAllBySpecificationId(Integer id);
}
