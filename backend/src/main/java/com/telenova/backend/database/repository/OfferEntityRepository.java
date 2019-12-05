package com.telenova.backend.database.repository;

import com.telenova.backend.database.entity.OfferEntity;
import com.telenova.backend.database.entity.SpecificationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferEntityRepository extends CrudRepository<OfferEntity, Integer> {
    List<OfferEntity> findAllBySpecification(SpecificationEntity specification);
}
