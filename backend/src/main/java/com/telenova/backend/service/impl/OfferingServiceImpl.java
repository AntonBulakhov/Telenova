package com.telenova.backend.service.impl;

import com.telenova.backend.database.entity.OfferingEntity;
import com.telenova.backend.database.entity.OfferingTypeEntity;
import com.telenova.backend.database.entity.ValueMeasureEntity;
import com.telenova.backend.database.repository.OfferHasOfferingEntityRepository;
import com.telenova.backend.database.repository.OfferingEntityRepository;
import com.telenova.backend.database.repository.OfferingTypeEntityRepository;
import com.telenova.backend.database.repository.ValueMeasureEntityRepository;
import com.telenova.backend.service.OfferingService;
import com.telenova.backend.web.dto.GroupedOfferings;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Service
public class OfferingServiceImpl implements OfferingService {

    private OfferingEntityRepository offeringEntityRepository;
    private OfferingTypeEntityRepository offeringTypeEntityRepository;
    private ValueMeasureEntityRepository valueMeasureEntityRepository;
    private OfferHasOfferingEntityRepository offerHasOfferingEntityRepository;

    @Override
    public GroupedOfferings getGroupedOfferingsByTypes() {
        MultiValuedMap<Integer, OfferingEntity> multiValuedMap = new ArrayListValuedHashMap<>();
        List<OfferingEntity> offerings = (List<OfferingEntity>) offeringEntityRepository.findAll();
        for (OfferingEntity offering : offerings) {
            multiValuedMap.put(offering.getOfferingType().getId(), offering);
        }

        GroupedOfferings groupedOfferings = new GroupedOfferings();
        groupedOfferings.setMobileInternet((List<OfferingEntity>) multiValuedMap.get(1));
        groupedOfferings.setMobileMinutesIn((List<OfferingEntity>) multiValuedMap.get(2));
        groupedOfferings.setMobileMinutesOut((List<OfferingEntity>) multiValuedMap.get(3));
        groupedOfferings.setInternetSpeed((List<OfferingEntity>) multiValuedMap.get(4));
        groupedOfferings.setInternetEquipment((List<OfferingEntity>) multiValuedMap.get(5));
        groupedOfferings.setInternetSoft((List<OfferingEntity>) multiValuedMap.get(6));

        return groupedOfferings;
    }

    @Override
    public List<OfferingTypeEntity> getAllOfferingTypes() {
        return (List<OfferingTypeEntity>) offeringTypeEntityRepository.findAll();
    }

    @Override
    public List<ValueMeasureEntity> getAllValueMeasures() {
        return (List<ValueMeasureEntity>) valueMeasureEntityRepository.findAll();
    }

    @Override
    public boolean createOffering(OfferingEntity offeringEntity) {
        List<OfferingEntity> offeringEntities = offeringEntityRepository.getAllByValueAndOfferingType(
                offeringEntity.getValue(), offeringEntity.getOfferingType());
        if (CollectionUtils.isNotEmpty(offeringEntities)) {
            return false;
        }
        return offeringEntityRepository.save(offeringEntity) != null;
    }

    @Override
    public void deleteOffering(Integer id) {
        offerHasOfferingEntityRepository.deleteAllByOfferingId(id);
        offeringEntityRepository.deleteById(id);
    }

    @Autowired
    public void setOfferingEntityRepository(OfferingEntityRepository offeringEntityRepository) {
        this.offeringEntityRepository = offeringEntityRepository;
    }

    @Autowired
    public void setOfferingTypeEntityRepository(OfferingTypeEntityRepository offeringTypeEntityRepository) {
        this.offeringTypeEntityRepository = offeringTypeEntityRepository;
    }

    @Autowired
    public void setValueMeasureEntityRepository(ValueMeasureEntityRepository valueMeasureEntityRepository) {
        this.valueMeasureEntityRepository = valueMeasureEntityRepository;
    }

    @Autowired
    public void setOfferHasOfferingEntityRepository(OfferHasOfferingEntityRepository offerHasOfferingEntityRepository) {
        this.offerHasOfferingEntityRepository = offerHasOfferingEntityRepository;
    }
}
