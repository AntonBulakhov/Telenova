package com.telenova.backend.web.dto;

import com.telenova.backend.database.entity.PhoneNumberEntity;
import com.telenova.backend.database.entity.ServiceEntity;

public class NewMobileService {
    private ServiceEntity service;
    private PhoneNumberEntity phoneNumber;

    public ServiceEntity getService() {
        return service;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }

    public PhoneNumberEntity getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumberEntity phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
