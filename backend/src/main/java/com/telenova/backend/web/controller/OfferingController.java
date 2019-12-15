package com.telenova.backend.web.controller;

import com.telenova.backend.database.entity.OfferingEntity;
import com.telenova.backend.database.entity.OfferingTypeEntity;
import com.telenova.backend.database.entity.ValueMeasureEntity;
import com.telenova.backend.service.OfferingService;
import com.telenova.backend.web.dto.GroupedOfferings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/offering")
public class OfferingController {

    private OfferingService offeringService;

    @PreAuthorize("hasAnyRole('SUPER', 'ADMIN', 'EMPLOYEE')")
    @GetMapping("/grouped")
    public GroupedOfferings getGroupedOfferingsByTypes() {
        return offeringService.getGroupedOfferingsByTypes();
    }

    @PreAuthorize("hasAnyRole('SUPER', 'ADMIN', 'EMPLOYEE')")
    @GetMapping("/types")
    public List<OfferingTypeEntity> getAllOfferingTypes() {
        return offeringService.getAllOfferingTypes();
    }

    @PreAuthorize("hasAnyRole('SUPER', 'ADMIN', 'EMPLOYEE')")
    @GetMapping("/value/measures")
    public List<ValueMeasureEntity> getAllValueMeasures() {
        return offeringService.getAllValueMeasures();
    }

    @PreAuthorize("hasAnyRole('SUPER', 'ADMIN', 'EMPLOYEE')")
    @PostMapping
    public Boolean createOffering(@RequestBody OfferingEntity offeringEntity) {
        return offeringService.createOffering(offeringEntity);
    }

    @PreAuthorize("hasAnyRole('SUPER', 'ADMIN', 'EMPLOYEE')")
    @DeleteMapping("/{id}")
    public void deleteOffering(@PathVariable Integer id) {
        offeringService.deleteOffering(id);
    }

    @PreAuthorize("hasAnyRole('SUPER', 'ADMIN', 'EMPLOYEE')")
    @GetMapping("/specification/{id}")
    public GroupedOfferings getGroupedOfferingsBySpecId(@PathVariable Integer id) {
        return offeringService.getGroupedOfferingsBySpecId(id);
    }

    @Autowired
    public void setOfferingService(OfferingService offeringService) {
        this.offeringService = offeringService;
    }
}
