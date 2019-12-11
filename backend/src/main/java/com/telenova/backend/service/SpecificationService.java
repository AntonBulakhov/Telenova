package com.telenova.backend.service;

import com.telenova.backend.database.entity.SpecificationEntity;

import java.util.List;

public interface SpecificationService {
    List<SpecificationEntity> getAllSpecifications();
}
