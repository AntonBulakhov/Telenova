package com.telenova.backend.charging;

import com.telenova.backend.database.entity.BalanceEntity;
import com.telenova.backend.database.entity.OfferEntity;
import com.telenova.backend.database.entity.ServiceEntity;
import com.telenova.backend.database.entity.ServiceStatusEntity;
import com.telenova.backend.database.repository.OfferEntityRepository;
import com.telenova.backend.database.repository.ServiceEntityRepository;
import com.telenova.backend.database.repository.ServiceStatusEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.telenova.backend.constants.ServiceStatusConstants.ACTIVE_SERVICE_STATUS_ID;
import static com.telenova.backend.constants.ServiceStatusConstants.BLOCKED_SERVICE_STATUS_ID;
import static com.telenova.backend.constants.SpecificationConstants.INTERNET_SPECIFICATION_ID;
import static com.telenova.backend.constants.SpecificationConstants.MOBILE_SPECIFICATION_ID;

@Component
public class ChargingService {

    private ServiceEntityRepository serviceEntityRepository;
    private OfferEntityRepository offerEntityRepository;
    private ServiceStatusEntityRepository serviceStatusEntityRepository;

    private static final double MINIMAL_BALANCE = -25;

    @Scheduled(cron = "0 0 1 * * ?")
    public void chargeMoney() {
        ServiceStatusEntity activeStatus = serviceStatusEntityRepository.findById(ACTIVE_SERVICE_STATUS_ID).get();
        List<ServiceEntity> services = serviceEntityRepository.findAllByServiceStatus(activeStatus);

        for (ServiceEntity service : services) {
            OfferEntity offer = offerEntityRepository.findById(service.getOfferId()).get();
            BalanceEntity balance = service.getBalance();
            double balanceValue = balance.getValue();
            balanceValue -= offer.getPrice();
            balance.setValue(balanceValue);
            service.setBalance(balance);

            if ((INTERNET_SPECIFICATION_ID == offer.getSpecification().getId() && balanceValue < 0)
                    || (MOBILE_SPECIFICATION_ID == offer.getSpecification().getId() && balanceValue < MINIMAL_BALANCE)) {
                service.setServiceStatus(serviceStatusEntityRepository.findById(BLOCKED_SERVICE_STATUS_ID).get());
            } else {
                service.setServiceStatus(activeStatus);
            }

            serviceEntityRepository.save(service);
        }
    }

    @Autowired
    public void setServiceEntityRepository(ServiceEntityRepository serviceEntityRepository) {
        this.serviceEntityRepository = serviceEntityRepository;
    }

    @Autowired
    public void setOfferEntityRepository(OfferEntityRepository offerEntityRepository) {
        this.offerEntityRepository = offerEntityRepository;
    }

    @Autowired
    public void setServiceStatusEntityRepository(ServiceStatusEntityRepository serviceStatusEntityRepository) {
        this.serviceStatusEntityRepository = serviceStatusEntityRepository;
    }
}
