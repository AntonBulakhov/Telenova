package com.telenova.backend.service;

import com.telenova.backend.database.entity.OfferingEntity;
import com.telenova.backend.database.entity.OfferingTypeEntity;
import com.telenova.backend.database.entity.ValueMeasureEntity;
import com.telenova.backend.web.dto.GroupedOfferings;

import java.util.List;

public interface OfferingService {
    GroupedOfferings getGroupedOfferingsByTypes();

    List<OfferingTypeEntity> getAllOfferingTypes();

    List<ValueMeasureEntity> getAllValueMeasures();

    boolean createOffering(OfferingEntity offeringEntity);

    void deleteOffering(Integer id);
}
