package com.telenova.backend.web.dto;

import com.telenova.backend.database.entity.OfferEntity;
import com.telenova.backend.database.entity.PhoneNumberEntity;
import com.telenova.backend.database.entity.ServiceEntity;

public class ProfileMobileOffer {
    private ServiceEntity service;
    private OfferEntity offer;
    private PhoneNumberEntity phoneNumber;

    public ServiceEntity getService() {
        return service;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }

    public OfferEntity getOffer() {
        return offer;
    }

    public void setOffer(OfferEntity offer) {
        this.offer = offer;
    }

    public PhoneNumberEntity getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumberEntity phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
