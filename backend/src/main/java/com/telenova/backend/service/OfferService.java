package com.telenova.backend.service;

import com.telenova.backend.web.dto.OfferDto;

import java.util.List;

public interface OfferService {
    List<OfferDto> getMainMobileOffers();
}
