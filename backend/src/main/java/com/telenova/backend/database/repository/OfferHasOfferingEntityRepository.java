package com.telenova.backend.database.repository;

import com.telenova.backend.database.entity.OfferHasOfferingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferHasOfferingEntityRepository extends CrudRepository<OfferHasOfferingEntity, Integer> {
    List<OfferHasOfferingEntity> getOfferHasOfferingEntitiesByOfferId(Integer offerId);
    void deleteAllByOfferingId(Integer id);
}
