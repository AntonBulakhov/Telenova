package com.telenova.backend.web.controller;

import com.telenova.backend.database.entity.OfferEntity;
import com.telenova.backend.database.entity.OfferStatusEntity;
import com.telenova.backend.service.OfferService;
import com.telenova.backend.web.dto.InternetOfferDto;
import com.telenova.backend.web.dto.MobileOfferDto;
import com.telenova.backend.web.dto.OfferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/offer")
public class OfferController {

    private static final int PAGE_SIZE = 6;

    private OfferService offerService;

    @GetMapping("/mobile/main")
    public List<MobileOfferDto> getMainOffers() {
        return offerService.getMainMobileOffers();
    }

    @GetMapping("/mobile/all")
    public List<MobileOfferDto> getAllMobileOffers() {
        return offerService.getAllMobileOffers();
    }

    @GetMapping("/internet/all")
    public List<InternetOfferDto> getAllInternetOffers() {
        return offerService.getAllInternetOffers();
    }

    @PreAuthorize("hasAnyRole('SUPER', 'ADMIN', 'EMPLOYEE')")
    @PostMapping("/mobile")
    public Boolean createMobileOffer(@RequestBody MobileOfferDto mobileOfferDto) {
        return offerService.createMobileOffer(mobileOfferDto);
    }

    @PreAuthorize("hasAnyRole('SUPER', 'ADMIN', 'EMPLOYEE')")
    @PostMapping("/internet")
    public Boolean createInternetOffer(@RequestBody InternetOfferDto internetOfferDto) {
        return offerService.createInternetOffer(internetOfferDto);
    }

    @GetMapping("/statuses")
    public List<OfferStatusEntity> getAllStatuses() {
        return offerService.getAllOfferStatuses();
    }

    @PreAuthorize("hasAnyRole('SUPER', 'ADMIN', 'EMPLOYEE')")
    @PostMapping("/status")
    public void setOfferStatus(@RequestBody OfferEntity offerStatus) {
        offerService.setOfferStatus(offerStatus);
    }

    @GetMapping("/mobile/{id}")
    public MobileOfferDto getMobileOfferById(@PathVariable Integer id) {
        return offerService.getMobileOfferById(id);
    }

    @GetMapping(value = "/mobile/offers", params = "page")
    public Page<MobileOfferDto> getMobileOffersPage(@RequestParam Integer page) {
        return offerService.getAllMobilePage(page, PAGE_SIZE);
    }

    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }
}
