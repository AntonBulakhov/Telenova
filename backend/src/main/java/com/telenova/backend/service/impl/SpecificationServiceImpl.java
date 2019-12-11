package com.telenova.backend.service.impl;

import com.telenova.backend.database.entity.SpecificationEntity;
import com.telenova.backend.database.repository.SpecificationEntityRepository;
import com.telenova.backend.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    private SpecificationEntityRepository specificationEntityRepository;

    @Override
    public List<SpecificationEntity> getAllSpecifications() {
        return (List<SpecificationEntity>) specificationEntityRepository.findAll();
    }

    @Autowired
    public void setSpecificationEntityRepository(SpecificationEntityRepository specificationEntityRepository) {
        this.specificationEntityRepository = specificationEntityRepository;
    }
}
