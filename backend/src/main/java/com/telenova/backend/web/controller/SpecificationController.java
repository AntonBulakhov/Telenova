package com.telenova.backend.web.controller;

import com.telenova.backend.database.entity.SpecificationEntity;
import com.telenova.backend.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/specification")
public class SpecificationController {

    private SpecificationService specificationService;

    @GetMapping("/all")
    public List<SpecificationEntity> getAllSpecifications() {
        return specificationService.getAllSpecifications();
    }

    @Autowired
    public void setSpecificationService(SpecificationService specificationService) {
        this.specificationService = specificationService;
    }
}
