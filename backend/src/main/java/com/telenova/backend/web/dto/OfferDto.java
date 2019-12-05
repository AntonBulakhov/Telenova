package com.telenova.backend.web.dto;

import com.telenova.backend.database.entity.OfferEntity;
import com.telenova.backend.database.entity.OfferingEntity;

import java.util.List;

public class OfferDto {
    private OfferEntity offer;
    private List<OfferingEntity> offerings;

    public OfferEntity getOffer() {
        return offer;
    }

    public void setOffer(OfferEntity offer) {
        this.offer = offer;
    }

    public List<OfferingEntity> getOfferings() {
        return offerings;
    }

    public void setOfferings(List<OfferingEntity> offerings) {
        this.offerings = offerings;
    }
}
