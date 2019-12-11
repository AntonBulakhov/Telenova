package com.telenova.backend.web.controller;

import com.telenova.backend.database.entity.OfferingEntity;
import com.telenova.backend.database.entity.OfferingTypeEntity;
import com.telenova.backend.database.entity.ValueMeasureEntity;
import com.telenova.backend.service.OfferingService;
import com.telenova.backend.web.dto.GroupedOfferings;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/grouped")
    public GroupedOfferings getGroupedOfferingsByTypes() {
        return offeringService.getGroupedOfferingsByTypes();
    }

    @GetMapping("/types")
    public List<OfferingTypeEntity> getAllOfferingTypes() {
        return offeringService.getAllOfferingTypes();
    }

    @GetMapping("/value/measures")
    public List<ValueMeasureEntity> getAllValueMeasures() {
        return offeringService.getAllValueMeasures();
    }

    @PostMapping
    public Boolean createOffering(@RequestBody OfferingEntity offeringEntity) {
        return offeringService.createOffering(offeringEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteOffering(@PathVariable Integer id) {
        offeringService.deleteOffering(id);
    }

    @GetMapping("/specification/{id}")
    public GroupedOfferings getGroupedOfferingsBySpecId(@PathVariable Integer id) {
        return offeringService.getGroupedOfferingsBySpecId(id);
    }

    @Autowired
    public void setOfferingService(OfferingService offeringService) {
        this.offeringService = offeringService;
    }
}
