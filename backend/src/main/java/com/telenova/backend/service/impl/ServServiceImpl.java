package com.telenova.backend.service.impl;

import com.telenova.backend.database.entity.BalanceEntity;
import com.telenova.backend.database.entity.OfferEntity;
import com.telenova.backend.database.entity.PhoneNumberEntity;
import com.telenova.backend.database.entity.ServiceEntity;
import com.telenova.backend.database.entity.ServiceStatusEntity;
import com.telenova.backend.database.repository.BalanceEntityRepository;
import com.telenova.backend.database.repository.OfferEntityRepository;
import com.telenova.backend.database.repository.PhoneNumberEntityRepository;
import com.telenova.backend.database.repository.ServiceEntityRepository;
import com.telenova.backend.database.repository.ServiceStatusEntityRepository;
import com.telenova.backend.service.ServService;
import com.telenova.backend.web.dto.NewMobileService;
import com.telenova.backend.web.dto.ProfileMobileOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServServiceImpl implements ServService {

    private ServiceEntityRepository serviceEntityRepository;
    private ServiceStatusEntityRepository serviceStatusEntityRepository;
    private BalanceEntityRepository balanceEntityRepository;
    private PhoneNumberEntityRepository phoneNumberEntityRepository;
    private OfferEntityRepository offerEntityRepository;

    @Override
    public List<ServiceStatusEntity> getAllStatuses() {
        return (List<ServiceStatusEntity>) serviceStatusEntityRepository.findAll();
    }

    @Override
    public Boolean createMobileService(NewMobileService mobileService) {
        BalanceEntity balanceEntity = new BalanceEntity();
        balanceEntity.setValue(0);
        balanceEntity = balanceEntityRepository.save(balanceEntity);
        ServiceEntity serviceEntity = mobileService.getService();
        serviceEntity.setUserId(1); // stub for now
        serviceEntity.setBalance(balanceEntity);
        serviceEntity = serviceEntityRepository.save(serviceEntity);
        PhoneNumberEntity phoneNumberEntity = mobileService.getPhoneNumber();
        phoneNumberEntity.setUserId(1); // stub for now
        phoneNumberEntity.setServiceId(serviceEntity.getId());
        phoneNumberEntityRepository.save(phoneNumberEntity);

        return serviceEntity != null || phoneNumberEntity != null || balanceEntity != null;
    }

    @Override
    public List<ProfileMobileOffer> getMobileServicesByUserId(Integer id) {
        List<ProfileMobileOffer> profileMobileOffers = new ArrayList<>();

        List<ServiceEntity> serviceEntities = serviceEntityRepository.findAllByUserId(id);
        for (ServiceEntity serviceEntity : serviceEntities) {
            PhoneNumberEntity phoneNumberEntity = phoneNumberEntityRepository.findByServiceId(serviceEntity.getId());
            OfferEntity offerEntity = offerEntityRepository.findById(serviceEntity.getOfferId()).get();

            ProfileMobileOffer profileMobileOffer = new ProfileMobileOffer();
            profileMobileOffer.setService(serviceEntity);
            profileMobileOffer.setPhoneNumber(phoneNumberEntity);
            profileMobileOffer.setOffer(offerEntity);

            profileMobileOffers.add(profileMobileOffer);
        }

        return profileMobileOffers;
    }

    @Override
    public Boolean fillBalance(BalanceEntity balanceEntity) {
        BalanceEntity balance = balanceEntityRepository.findById(balanceEntity.getId()).get();
        balance.setValue(balance.getValue() + balanceEntity.getValue());
        return balanceEntityRepository.save(balance) != null;
    }

    @Autowired
    public void setServiceEntityRepository(ServiceEntityRepository serviceEntityRepository) {
        this.serviceEntityRepository = serviceEntityRepository;
    }

    @Autowired
    public void setServiceStatusEntityRepository(ServiceStatusEntityRepository serviceStatusEntityRepository) {
        this.serviceStatusEntityRepository = serviceStatusEntityRepository;
    }

    @Autowired
    public void setBalanceEntityRepository(BalanceEntityRepository balanceEntityRepository) {
        this.balanceEntityRepository = balanceEntityRepository;
    }

    @Autowired
    public void setPhoneNumberEntityRepository(PhoneNumberEntityRepository phoneNumberEntityRepository) {
        this.phoneNumberEntityRepository = phoneNumberEntityRepository;
    }

    @Autowired
    public void setOfferEntityRepository(OfferEntityRepository offerEntityRepository) {
        this.offerEntityRepository = offerEntityRepository;
    }
}
