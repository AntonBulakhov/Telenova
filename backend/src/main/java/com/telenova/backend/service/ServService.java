package com.telenova.backend.service;

import com.telenova.backend.database.entity.BalanceEntity;
import com.telenova.backend.database.entity.ServiceEntity;
import com.telenova.backend.database.entity.ServiceStatusEntity;
import com.telenova.backend.web.dto.InternetServiceOfferModel;
import com.telenova.backend.web.dto.NewMobileService;
import com.telenova.backend.web.dto.ProfileMobileOffer;

import java.util.List;

public interface ServService {
    List<ServiceStatusEntity> getAllStatuses();

    Boolean createMobileService(NewMobileService mobileService);

    List<ProfileMobileOffer> getMobileServicesByUserId(Integer id);

    Boolean fillBalance(BalanceEntity balanceEntity);

    Boolean createInternetService(ServiceEntity serviceEntity);

    List<InternetServiceOfferModel> getInternetServicesByStatus(Integer id);

    void deleteService(Integer id);

    Boolean setServiceStatus(ServiceEntity serviceEntity);
}
