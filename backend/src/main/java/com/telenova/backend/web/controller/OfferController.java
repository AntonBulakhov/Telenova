package com.telenova.backend.web.controller;

import com.telenova.backend.service.OfferService;
import com.telenova.backend.web.dto.OfferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/offer")
public class OfferController {

    private OfferService offerService;

    @GetMapping("/mobile/main")
    public List<OfferDto> getMainOffers() {
        return offerService.getMainMobileOffers();
    }

    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }
}
