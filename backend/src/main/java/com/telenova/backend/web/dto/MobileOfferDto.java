package com.telenova.backend.web.dto;

import com.telenova.backend.database.entity.OfferEntity;
import com.telenova.backend.database.entity.OfferingEntity;

public class MobileOfferDto {
    private OfferEntity offer;
    private OfferingEntity mobileInternet;
    private OfferingEntity mobileMinutesIn;
    private OfferingEntity mobileMinutesOut;

    public OfferEntity getOffer() {
        return offer;
    }

    public void setOffer(OfferEntity offer) {
        this.offer = offer;
    }

    public OfferingEntity getMobileInternet() {
        return mobileInternet;
    }

    public void setMobileInternet(OfferingEntity mobileInternet) {
        this.mobileInternet = mobileInternet;
    }

    public OfferingEntity getMobileMinutesIn() {
        return mobileMinutesIn;
    }

    public void setMobileMinutesIn(OfferingEntity mobileMinutesIn) {
        this.mobileMinutesIn = mobileMinutesIn;
    }

    public OfferingEntity getMobileMinutesOut() {
        return mobileMinutesOut;
    }

    public void setMobileMinutesOut(OfferingEntity mobileMinutesOut) {
        this.mobileMinutesOut = mobileMinutesOut;
    }
}
