package com.telenova.backend.web.controller;

import com.telenova.backend.database.entity.ServiceStatusEntity;
import com.telenova.backend.service.ServService;
import com.telenova.backend.web.dto.NewMobileService;
import com.telenova.backend.web.dto.ProfileMobileOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/service")
public class ServiceController {

    private ServService servService;

    @GetMapping("/statuses")
    public List<ServiceStatusEntity> getAllStatuses() {
        return servService.getAllStatuses();
    }

    @PostMapping("/mobile")
    public Boolean createNewService(@RequestBody NewMobileService mobileService) {
        return servService.createMobileService(mobileService);
    }

    @GetMapping("/mobile/user/{id}")
    public List<ProfileMobileOffer> getMobileServicesByUserId(@PathVariable Integer id) {
        return servService.getMobileServicesByUserId(id);
    }

    @Autowired
    public void setServService(ServService servService) {
        this.servService = servService;
    }
}
