package com.telenova.backend.web.controller;

import com.telenova.backend.database.entity.BalanceEntity;
import com.telenova.backend.database.entity.ServiceEntity;
import com.telenova.backend.database.entity.ServiceStatusEntity;
import com.telenova.backend.service.ServService;
import com.telenova.backend.web.dto.InternetServiceOfferModel;
import com.telenova.backend.web.dto.NewMobileService;
import com.telenova.backend.web.dto.ProfileMobileOffer;
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

    @PostMapping("/balance")
    public Boolean fillBalance(@RequestBody BalanceEntity balanceEntity) {
        return servService.fillBalance(balanceEntity);
    }

    @PostMapping("/internet")
    public Boolean createInternetService(@RequestBody ServiceEntity serviceEntity) {
        return servService.createInternetService(serviceEntity);
    }

    @GetMapping("/internet/all/status/{id}")
    public List<InternetServiceOfferModel> getInternetServicesByStatus(@PathVariable Integer id) {
        return servService.getInternetServicesByStatus(id);
    }

    @DeleteMapping("/internet/{id}")
    public void deleteService(@PathVariable Integer id) {
        servService.deleteService(id);
    }

    @PostMapping("/status")
    public Boolean setServiceStatus(@RequestBody ServiceEntity serviceEntity) {
        return servService.setServiceStatus(serviceEntity);
    }

    @Autowired
    public void setServService(ServService servService) {
        this.servService = servService;
    }
}
