package com.telenova.backend.service;

import com.telenova.backend.database.entity.OfferEntity;
import com.telenova.backend.database.entity.OfferStatusEntity;
import com.telenova.backend.web.dto.InternetOfferDto;
import com.telenova.backend.web.dto.MobileOfferDto;

import java.util.List;

public interface OfferService {
    List<MobileOfferDto> getMainMobileOffers();

    List<MobileOfferDto> getAllMobileOffers();

    List<InternetOfferDto> getAllInternetOffers();

    Boolean createMobileOffer(MobileOfferDto mobileOfferDto);

    Boolean createInternetOffer(InternetOfferDto internetOfferDto);

    List<OfferStatusEntity> getAllOfferStatuses();

    MobileOfferDto getMobileOfferById(Integer id);

    void setOfferStatus(OfferEntity offerStatus);

    InternetOfferDto getInternetOfferDto(OfferEntity offerEntity);
}
