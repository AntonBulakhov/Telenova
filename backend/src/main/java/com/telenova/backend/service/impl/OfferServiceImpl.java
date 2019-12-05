package com.telenova.backend.service.impl;

import com.telenova.backend.database.entity.OfferEntity;
import com.telenova.backend.database.entity.OfferHasOfferingEntity;
import com.telenova.backend.database.entity.OfferingEntity;
import com.telenova.backend.database.entity.SpecificationEntity;
import com.telenova.backend.database.repository.OfferEntityRepository;
import com.telenova.backend.database.repository.OfferHasOfferingEntityRepository;
import com.telenova.backend.database.repository.OfferingEntityRepository;
import com.telenova.backend.database.repository.SpecificationEntityRepository;
import com.telenova.backend.service.OfferService;
import com.telenova.backend.web.dto.OfferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.telenova.backend.constants.SpecificationConstants.MOBILE_SPECIFICATION;

@Service
public class OfferServiceImpl implements OfferService {
    private OfferEntityRepository offerEntityRepository;
    private OfferingEntityRepository offeringEntityRepository;
    private OfferHasOfferingEntityRepository offerHasOfferingEntityRepository;
    private SpecificationEntityRepository specificationEntityRepository;

    @Override
    public List<OfferDto> getMainMobileOffers() {
        Optional<SpecificationEntity> specification = specificationEntityRepository.findById(MOBILE_SPECIFICATION);
        List<OfferDto> offerDtos = new ArrayList<>();
        if (specification.isPresent()) {
            List<OfferEntity> offerEntities = offerEntityRepository.findAllBySpecification(specification.get());
            for (OfferEntity offerEntity : offerEntities) {
                OfferDto offerDto = new OfferDto();
                offerDto.setOffer(offerEntity);
                List<OfferingEntity> offeringEntities = getOfferingsByOfferId(offerEntity.getId());
                offerDto.setOfferings(offeringEntities);
                offerDtos.add(offerDto);
            }
        }
        return offerDtos;
    }

    private List<OfferingEntity> getOfferingsByOfferId(Integer offerId) {
        List<OfferingEntity> offeringEntities = new ArrayList<>();

        List<OfferHasOfferingEntity> offerHasOfferings = offerHasOfferingEntityRepository
                .getOfferHasOfferingEntitiesByOfferId(offerId);
        for (OfferHasOfferingEntity offerToOffering : offerHasOfferings) {
            Optional<OfferingEntity> offeringEntity = offeringEntityRepository.findById(offerToOffering.getOfferingId());
            offeringEntities.add(offeringEntity.get());
        }

        return offeringEntities;
    }

    @Autowired
    public void setOfferEntityRepository(OfferEntityRepository offerEntityRepository) {
        this.offerEntityRepository = offerEntityRepository;
    }

    @Autowired
    public void setOfferingEntityRepository(OfferingEntityRepository offeringEntityRepository) {
        this.offeringEntityRepository = offeringEntityRepository;
    }

    @Autowired
    public void setOfferHasOfferingEntityRepository(OfferHasOfferingEntityRepository offerHasOfferingEntityRepository) {
        this.offerHasOfferingEntityRepository = offerHasOfferingEntityRepository;
    }

    @Autowired
    public void setSpecificationEntityRepository(SpecificationEntityRepository specificationEntityRepository) {
        this.specificationEntityRepository = specificationEntityRepository;
    }
}
