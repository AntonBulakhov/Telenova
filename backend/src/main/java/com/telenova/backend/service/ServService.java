package com.telenova.backend.service;

import com.telenova.backend.database.entity.ServiceStatusEntity;
import com.telenova.backend.web.dto.NewMobileService;

import java.util.List;

public interface ServService {
    List<ServiceStatusEntity> getAllStatuses();

    Boolean createMobileService(NewMobileService mobileService);
}
