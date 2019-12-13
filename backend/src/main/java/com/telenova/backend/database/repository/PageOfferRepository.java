package com.telenova.backend.database.repository;

import com.telenova.backend.database.entity.OfferEntity;
import com.telenova.backend.database.entity.OfferStatusEntity;
import com.telenova.backend.database.entity.SpecificationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageOfferRepository extends PagingAndSortingRepository<OfferEntity, Integer> {

    Page<OfferEntity> getAllBySpecificationAndOfferStatusNot(SpecificationEntity specification, OfferStatusEntity status, Pageable pageable);
}
