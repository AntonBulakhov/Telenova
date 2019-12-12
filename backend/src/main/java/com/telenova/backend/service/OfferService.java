package com.telenova.backend.service;

import com.telenova.backend.database.entity.OfferStatusEntity;
import com.telenova.backend.web.dto.InternetOfferDto;
import com.telenova.backend.web.dto.MobileOfferDto;
import com.telenova.backend.web.dto.OfferDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OfferService {
    List<MobileOfferDto> getMainMobileOffers();
    List<MobileOfferDto> getAllMobileOffers();
    List<InternetOfferDto> getAllInternetOffers();
    Boolean createMobileOffer(MobileOfferDto mobileOfferDto);
    Boolean createInternetOffer(InternetOfferDto internetOfferDto);
    List<OfferStatusEntity> getAllOfferStatuses();
}
