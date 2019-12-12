package com.telenova.backend.service.impl;

import com.telenova.backend.database.entity.OfferEntity;
import com.telenova.backend.database.entity.OfferHasOfferingEntity;
import com.telenova.backend.database.entity.OfferStatusEntity;
import com.telenova.backend.database.entity.OfferingEntity;
import com.telenova.backend.database.entity.SpecificationEntity;
import com.telenova.backend.database.repository.OfferEntityRepository;
import com.telenova.backend.database.repository.OfferHasOfferingEntityRepository;
import com.telenova.backend.database.repository.OfferStatusEntityRepository;
import com.telenova.backend.database.repository.OfferingEntityRepository;
import com.telenova.backend.database.repository.SpecificationEntityRepository;
import com.telenova.backend.service.OfferService;
import com.telenova.backend.service.OfferingService;
import com.telenova.backend.web.dto.GroupedOfferings;
import com.telenova.backend.web.dto.InternetOfferDto;
import com.telenova.backend.web.dto.MobileOfferDto;
import com.telenova.backend.web.dto.OfferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.telenova.backend.constants.SpecificationConstants.INTERNET_SPECIFICATION_ID;
import static com.telenova.backend.constants.SpecificationConstants.MOBILE_SPECIFICATION_ID;

@Service
public class OfferServiceImpl implements OfferService {
    private OfferEntityRepository offerEntityRepository;
    private OfferingEntityRepository offeringEntityRepository;
    private OfferHasOfferingEntityRepository offerHasOfferingEntityRepository;
    private SpecificationEntityRepository specificationEntityRepository;
    private OfferStatusEntityRepository offerStatusEntityRepository;

    private OfferingService offeringService;

    @Override
    public List<MobileOfferDto> getMainMobileOffers() {
        Optional<SpecificationEntity> specification = specificationEntityRepository.findById(MOBILE_SPECIFICATION_ID);
        List<MobileOfferDto> offerDtos = new ArrayList<>();
        if (specification.isPresent()) {
            List<OfferEntity> offerEntities = offerEntityRepository.findAllBySpecification(specification.get());
            for (OfferEntity offerEntity : offerEntities) {
                MobileOfferDto offerDto = new MobileOfferDto();
                offerDto.setOffer(offerEntity);
                List<OfferingEntity> offeringEntities = getOfferingsByOfferId(offerEntity.getId());
                GroupedOfferings groupedOfferings = offeringService.getGroupedOfferings(offeringEntities);
                offerDto.setMobileInternet(groupedOfferings.getMobileInternet().get(0));
                offerDto.setMobileMinutesIn(groupedOfferings.getMobileMinutesIn().get(0));
                offerDto.setMobileMinutesOut(groupedOfferings.getMobileMinutesOut().get(0));

                offerDtos.add(offerDto);
            }
        }

        if (offerDtos.size() <= 3) {
            return offerDtos;
        } else {
            return offerDtos.subList(0, 3);
        }
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

    @Override
    public List<MobileOfferDto> getAllMobileOffers() {
        List<MobileOfferDto> mobileOfferDtos = new ArrayList<>();

        SpecificationEntity specificationEntity = specificationEntityRepository.findById(MOBILE_SPECIFICATION_ID).get();
        List<OfferEntity> offerEntities = offerEntityRepository.findAllBySpecification(specificationEntity);
        for (OfferEntity offerEntity : offerEntities) {
            List<OfferingEntity> offeringEntities = getOfferingsByOfferId(offerEntity.getId());
            GroupedOfferings groupedOfferings = offeringService.getGroupedOfferings(offeringEntities);

            MobileOfferDto mobileOfferDto = new MobileOfferDto();
            mobileOfferDto.setOffer(offerEntity);
            mobileOfferDto.setMobileInternet(groupedOfferings.getMobileInternet().get(0));
            mobileOfferDto.setMobileMinutesIn(groupedOfferings.getMobileMinutesIn().get(0));
            mobileOfferDto.setMobileMinutesOut(groupedOfferings.getMobileMinutesOut().get(0));

            mobileOfferDtos.add(mobileOfferDto);
        }

        return mobileOfferDtos;
    }

    @Override
    public List<InternetOfferDto> getAllInternetOffers() {
        List<InternetOfferDto> internetOfferDtos = new ArrayList<>();

        SpecificationEntity specificationEntity = specificationEntityRepository.findById(INTERNET_SPECIFICATION_ID).get();
        List<OfferEntity> offerEntities = offerEntityRepository.findAllBySpecification(specificationEntity);
        for (OfferEntity offerEntity : offerEntities) {
            List<OfferingEntity> offeringEntities = getOfferingsByOfferId(offerEntity.getId());
            GroupedOfferings groupedOfferings = offeringService.getGroupedOfferings(offeringEntities);


            InternetOfferDto internetOfferDto = new InternetOfferDto();
            internetOfferDto.setOffer(offerEntity);
            internetOfferDto.setInternetSpeed(groupedOfferings.getInternetSpeed().get(0));

            List<OfferingEntity> internetEquipment = groupedOfferings.getInternetEquipment();
            internetOfferDto.setInternetEquipment1(internetEquipment.get(0));
            internetOfferDto.setInternetEquipment2(internetEquipment.get(1));

            List<OfferingEntity> internetSoft = groupedOfferings.getInternetSoft();
            internetOfferDto.setInternetSoft1(internetSoft.get(0));
            internetOfferDto.setInternetSoft2(internetSoft.get(1));

            internetOfferDtos.add(internetOfferDto);
        }

        return internetOfferDtos;
    }

    @Override
    public Boolean createMobileOffer(MobileOfferDto mobileOfferDto) {
        if (offerEntityRepository.save(mobileOfferDto.getOffer()) == null) {
            return false;
        }
        OfferHasOfferingEntity offerHasOfferingEntity = new OfferHasOfferingEntity();

        offerHasOfferingEntity.setOfferId(mobileOfferDto.getOffer().getId());
        offerHasOfferingEntity.setOfferingId(mobileOfferDto.getMobileInternet().getId());
        offerHasOfferingEntityRepository.save(offerHasOfferingEntity);

        offerHasOfferingEntity.setOfferId(mobileOfferDto.getOffer().getId());
        offerHasOfferingEntity.setOfferingId(mobileOfferDto.getMobileMinutesIn().getId());
        offerHasOfferingEntityRepository.save(offerHasOfferingEntity);

        offerHasOfferingEntity.setOfferId(mobileOfferDto.getOffer().getId());
        offerHasOfferingEntity.setOfferingId(mobileOfferDto.getMobileMinutesOut().getId());
        offerHasOfferingEntityRepository.save(offerHasOfferingEntity);
        return true;
    }

    @Override
    public Boolean createInternetOffer(InternetOfferDto internetOfferDto) {
        if (offerEntityRepository.save(internetOfferDto.getOffer()) == null) {
            return false;
        }
        OfferHasOfferingEntity offerHasOfferingEntity = new OfferHasOfferingEntity();

        offerHasOfferingEntity.setOfferId(internetOfferDto.getOffer().getId());
        offerHasOfferingEntity.setOfferingId(internetOfferDto.getInternetSpeed().getId());
        offerHasOfferingEntityRepository.save(offerHasOfferingEntity);

        offerHasOfferingEntity.setOfferId(internetOfferDto.getOffer().getId());
        offerHasOfferingEntity.setOfferingId(internetOfferDto.getInternetEquipment1().getId());
        offerHasOfferingEntityRepository.save(offerHasOfferingEntity);

        offerHasOfferingEntity.setOfferId(internetOfferDto.getOffer().getId());
        offerHasOfferingEntity.setOfferingId(internetOfferDto.getInternetEquipment2().getId());
        offerHasOfferingEntityRepository.save(offerHasOfferingEntity);

        offerHasOfferingEntity.setOfferId(internetOfferDto.getOffer().getId());
        offerHasOfferingEntity.setOfferingId(internetOfferDto.getInternetSoft1().getId());
        offerHasOfferingEntityRepository.save(offerHasOfferingEntity);

        offerHasOfferingEntity.setOfferId(internetOfferDto.getOffer().getId());
        offerHasOfferingEntity.setOfferingId(internetOfferDto.getInternetSoft2().getId());
        offerHasOfferingEntityRepository.save(offerHasOfferingEntity);
        return true;
    }

    @Override
    public List<OfferStatusEntity> getAllOfferStatuses() {
        return (List<OfferStatusEntity>) offerStatusEntityRepository.findAll();
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

    @Autowired
    public void setOfferStatusEntityRepository(OfferStatusEntityRepository offerStatusEntityRepository) {
        this.offerStatusEntityRepository = offerStatusEntityRepository;
    }

    @Autowired
    public void setOfferingService(OfferingService offeringService) {
        this.offeringService = offeringService;
    }
}
