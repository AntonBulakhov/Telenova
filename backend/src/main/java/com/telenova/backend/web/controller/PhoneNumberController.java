package com.telenova.backend.web.controller;

import com.telenova.backend.database.entity.PhoneNumberEntity;
import com.telenova.backend.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/phone")
public class PhoneNumberController {

    private PhoneNumberService phoneNumberService;

    @GetMapping("/generate")
    public PhoneNumberEntity generatePhoneNumber() {
        return phoneNumberService.generatePhoneNumber();
    }

    @Autowired
    public void setPhoneNumberService(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }
}
