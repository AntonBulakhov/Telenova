package com.telenova.backend.service.impl;

import com.telenova.backend.database.entity.PhoneNumberEntity;
import com.telenova.backend.database.repository.PhoneNumberEntityRepository;
import com.telenova.backend.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.telenova.backend.constants.PhoneNumberConstants.CODE;
import static com.telenova.backend.constants.PhoneNumberConstants.COUNTRY_CODE;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    private PhoneNumberEntityRepository phoneNumberEntityRepository;

    @Override
    public PhoneNumberEntity generatePhoneNumber() {
        String phoneNumber;
        String existingNumber;
        do {
            phoneNumber = String.format("%s (%s) %d%d%d-%d%d-%d%d", COUNTRY_CODE, CODE,
                    new Random().nextInt(8 + 1) + 1,
                    new Random().nextInt(9 + 1),
                    new Random().nextInt(9 + 1),
                    new Random().nextInt(9 + 1),
                    new Random().nextInt(9 + 1),
                    new Random().nextInt(9 + 1),
                    new Random().nextInt(9 + 1)
            );
            PhoneNumberEntity phoneNumberEntity = phoneNumberEntityRepository.findByHoneNumber(phoneNumber);
            if (phoneNumberEntity != null) {
                existingNumber = phoneNumberEntity.getHoneNumber();
            } else {
                existingNumber = "null";
            }
        } while (phoneNumber.equals(existingNumber));

        PhoneNumberEntity phoneNumberEntity = new PhoneNumberEntity();
        phoneNumberEntity.setHoneNumber(phoneNumber);

        return phoneNumberEntity;
    }

    @Autowired
    public void setPhoneNumberEntityRepository(PhoneNumberEntityRepository phoneNumberEntityRepository) {
        this.phoneNumberEntityRepository = phoneNumberEntityRepository;
    }
}
