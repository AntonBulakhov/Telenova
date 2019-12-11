package com.telenova.backend.web.dto;

import com.telenova.backend.database.entity.OfferEntity;
import com.telenova.backend.database.entity.OfferingEntity;

public class InternetOfferDto {
    private OfferEntity offer;
    private OfferingEntity internetSpeed;
    private OfferingEntity internetEquipment1;
    private OfferingEntity internetEquipment2;
    private OfferingEntity internetSoft1;
    private OfferingEntity internetSoft2;

    public OfferEntity getOffer() {
        return offer;
    }

    public void setOffer(OfferEntity offer) {
        this.offer = offer;
    }

    public OfferingEntity getInternetSpeed() {
        return internetSpeed;
    }

    public void setInternetSpeed(OfferingEntity internetSpeed) {
        this.internetSpeed = internetSpeed;
    }

    public OfferingEntity getInternetEquipment1() {
        return internetEquipment1;
    }

    public void setInternetEquipment1(OfferingEntity internetEquipment1) {
        this.internetEquipment1 = internetEquipment1;
    }

    public OfferingEntity getInternetEquipment2() {
        return internetEquipment2;
    }

    public void setInternetEquipment2(OfferingEntity internetEquipment2) {
        this.internetEquipment2 = internetEquipment2;
    }

    public OfferingEntity getInternetSoft1() {
        return internetSoft1;
    }

    public void setInternetSoft1(OfferingEntity internetSoft1) {
        this.internetSoft1 = internetSoft1;
    }

    public OfferingEntity getInternetSoft2() {
        return internetSoft2;
    }

    public void setInternetSoft2(OfferingEntity internetSoft2) {
        this.internetSoft2 = internetSoft2;
    }
}
