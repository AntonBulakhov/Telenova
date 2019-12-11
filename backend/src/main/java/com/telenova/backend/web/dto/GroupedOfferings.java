package com.telenova.backend.web.dto;

import com.telenova.backend.database.entity.OfferingEntity;

import java.util.List;

public class GroupedOfferings {
    List<OfferingEntity> mobileInternet;
    List<OfferingEntity> mobileMinutesIn;
    List<OfferingEntity> mobileMinutesOut;
    List<OfferingEntity> internetSpeed;
    List<OfferingEntity> internetEquipment;
    List<OfferingEntity> internetSoft;

    public List<OfferingEntity> getMobileInternet() {
        return mobileInternet;
    }

    public void setMobileInternet(List<OfferingEntity> mobileInternet) {
        this.mobileInternet = mobileInternet;
    }

    public List<OfferingEntity> getMobileMinutesIn() {
        return mobileMinutesIn;
    }

    public void setMobileMinutesIn(List<OfferingEntity> mobileMinutesIn) {
        this.mobileMinutesIn = mobileMinutesIn;
    }

    public List<OfferingEntity> getMobileMinutesOut() {
        return mobileMinutesOut;
    }

    public void setMobileMinutesOut(List<OfferingEntity> mobileMinutesOut) {
        this.mobileMinutesOut = mobileMinutesOut;
    }

    public List<OfferingEntity> getInternetSpeed() {
        return internetSpeed;
    }

    public void setInternetSpeed(List<OfferingEntity> internetSpeed) {
        this.internetSpeed = internetSpeed;
    }

    public List<OfferingEntity> getInternetEquipment() {
        return internetEquipment;
    }

    public void setInternetEquipment(List<OfferingEntity> internetEquipment) {
        this.internetEquipment = internetEquipment;
    }

    public List<OfferingEntity> getInternetSoft() {
        return internetSoft;
    }

    public void setInternetSoft(List<OfferingEntity> internetSoft) {
        this.internetSoft = internetSoft;
    }
}
