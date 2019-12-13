package com.telenova.backend.web.dto;

import com.telenova.backend.database.entity.ServiceEntity;

public class InternetServiceOfferModel {
    private ServiceEntity service;
    private InternetOfferDto offer;

    public ServiceEntity getService() {
        return service;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }

    public InternetOfferDto getOffer() {
        return offer;
    }

    public void setOffer(InternetOfferDto offer) {
        this.offer = offer;
    }
}
