package com.telenova.backend.service.impl;

import com.telenova.backend.database.entity.AddressEntity;
import com.telenova.backend.database.entity.BalanceEntity;
import com.telenova.backend.database.entity.OfferEntity;
import com.telenova.backend.database.entity.PhoneNumberEntity;
import com.telenova.backend.database.entity.ServiceEntity;
import com.telenova.backend.database.entity.ServiceStatusEntity;
import com.telenova.backend.database.entity.SpecificationEntity;
import com.telenova.backend.database.repository.AddressEntityRepository;
import com.telenova.backend.database.repository.BalanceEntityRepository;
import com.telenova.backend.database.repository.OfferEntityRepository;
import com.telenova.backend.database.repository.PhoneNumberEntityRepository;
import com.telenova.backend.database.repository.ServiceEntityRepository;
import com.telenova.backend.database.repository.ServiceStatusEntityRepository;
import com.telenova.backend.database.repository.SpecificationEntityRepository;
import com.telenova.backend.service.OfferService;
import com.telenova.backend.service.ServService;
import com.telenova.backend.web.dto.InternetServiceOfferModel;
import com.telenova.backend.web.dto.NewMobileService;
import com.telenova.backend.web.dto.ProfileMobileOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.telenova.backend.constants.SpecificationConstants.INTERNET_SPECIFICATION_ID;
import static com.telenova.backend.constants.SpecificationConstants.MOBILE_SPECIFICATION_ID;

@Service
public class ServServiceImpl implements ServService {

    private ServiceEntityRepository serviceEntityRepository;
    private ServiceStatusEntityRepository serviceStatusEntityRepository;
    private BalanceEntityRepository balanceEntityRepository;
    private PhoneNumberEntityRepository phoneNumberEntityRepository;
    private OfferEntityRepository offerEntityRepository;
    private AddressEntityRepository addressEntityRepository;
    private SpecificationEntityRepository specificationEntityRepository;

    private OfferService offerService;

    @Override
    public List<ServiceStatusEntity> getAllStatuses() {
        return (List<ServiceStatusEntity>) serviceStatusEntityRepository.findAll();
    }

    @Override
    public Boolean createMobileService(NewMobileService mobileService) {
        BalanceEntity balanceEntity = new BalanceEntity();
        balanceEntity.setValue(0);
        balanceEntity = balanceEntityRepository.save(balanceEntity);
        ServiceEntity serviceEntity = mobileService.getService();
        serviceEntity.setUserId(mobileService.getService().getUserId());
        serviceEntity.setBalance(balanceEntity);
        serviceEntity = serviceEntityRepository.save(serviceEntity);
        PhoneNumberEntity phoneNumberEntity = mobileService.getPhoneNumber();
        phoneNumberEntity.setUserId(serviceEntity.getUserId());
        phoneNumberEntity.setServiceId(serviceEntity.getId());
        phoneNumberEntityRepository.save(phoneNumberEntity);

        return serviceEntity != null || phoneNumberEntity != null || balanceEntity != null;
    }

    @Override
    public List<ProfileMobileOffer> getMobileServicesByUserId(Integer id) {
        List<ProfileMobileOffer> profileMobileOffers = new ArrayList<>();

        List<Integer> offerIds = getOfferIdsBySpecification(MOBILE_SPECIFICATION_ID);

        List<ServiceEntity> serviceEntities = serviceEntityRepository.findAllByUserIdAndOfferIdIn(id, offerIds);
        for (ServiceEntity serviceEntity : serviceEntities) {
            PhoneNumberEntity phoneNumberEntity = phoneNumberEntityRepository.findByServiceId(serviceEntity.getId());
            OfferEntity offerEntity = offerEntityRepository.findById(serviceEntity.getOfferId()).get();

            ProfileMobileOffer profileMobileOffer = new ProfileMobileOffer();
            profileMobileOffer.setService(serviceEntity);
            profileMobileOffer.setPhoneNumber(phoneNumberEntity);
            profileMobileOffer.setOffer(offerEntity);

            profileMobileOffers.add(profileMobileOffer);
        }

        return profileMobileOffers;
    }

    public List<Integer> getOfferIdsBySpecification(Integer offerSpecification) {
        SpecificationEntity mobileSpecification = specificationEntityRepository.findById(offerSpecification).get();
        List<OfferEntity> offerEntities = offerEntityRepository.findAllBySpecification(mobileSpecification);
        List<Integer> offerIds = new ArrayList<>();
        for (OfferEntity offerEntity : offerEntities) {
            offerIds.add(offerEntity.getId());
        }
        return offerIds;
    }

    @Override
    public Boolean fillBalance(BalanceEntity balanceEntity) {
        BalanceEntity balance = balanceEntityRepository.findById(balanceEntity.getId()).get();
        balance.setValue(balance.getValue() + balanceEntity.getValue());
        return balanceEntityRepository.save(balance) != null;
    }

    @Override
    public Boolean createInternetService(ServiceEntity serviceEntity) {
        BalanceEntity balanceEntity = new BalanceEntity();
        balanceEntity.setValue(0);
        balanceEntity = balanceEntityRepository.save(balanceEntity);

        serviceEntity.setBalance(balanceEntity);

        AddressEntity addressEntity = addressEntityRepository.findByCityAndStreetAndHouseAndFlat(
                serviceEntity.getAddress().getCity(),
                serviceEntity.getAddress().getStreet(),
                serviceEntity.getAddress().getHouse(),
                serviceEntity.getAddress().getFlat()
        );
        if (addressEntity == null) {
            addressEntity = addressEntityRepository.save(serviceEntity.getAddress());
        }
        serviceEntity.setAddress(addressEntity);

        serviceEntity = serviceEntityRepository.save(serviceEntity);

        return serviceEntity.getId() != 0;
    }

    @Autowired
    public void setServiceEntityRepository(ServiceEntityRepository serviceEntityRepository) {
        this.serviceEntityRepository = serviceEntityRepository;
    }

    @Override
    public List<InternetServiceOfferModel> getInternetServicesByStatus(Integer statusId) {
        SpecificationEntity internetSpecification = specificationEntityRepository.findById(INTERNET_SPECIFICATION_ID).get();
        return getServiceEntities(internetSpecification, statusId);
    }

    private List<InternetServiceOfferModel> getServiceEntities(SpecificationEntity internetSpecification, Integer statusId) {
        List<OfferEntity> offerEntities = offerEntityRepository.findAllBySpecification(internetSpecification);
        ServiceStatusEntity serviceStatusEntity = serviceStatusEntityRepository.findById(statusId).get();

        List<Integer> offerIds = new ArrayList<>();
        for (OfferEntity offer : offerEntities) {
            offerIds.add(offer.getId());
        }

        List<ServiceEntity> serviceEntities = serviceEntityRepository.
                findAllByOfferIdInAndServiceStatus(offerIds, serviceStatusEntity);

        return getInternetServiceOfferModels(serviceEntities);
    }

    private List<InternetServiceOfferModel> getInternetServiceOfferModels(List<ServiceEntity> serviceEntities) {
        List<InternetServiceOfferModel> modelList = new ArrayList<>();
        for (ServiceEntity serviceEntity : serviceEntities) {
            OfferEntity offerEntity = offerEntityRepository.findById(serviceEntity.getOfferId()).get();

            InternetServiceOfferModel model = new InternetServiceOfferModel();
            model.setService(serviceEntity);
            model.setOffer(offerService.getInternetOfferDto(offerEntity));

            modelList.add(model);
        }
        return modelList;
    }

    @Override
    public void deleteService(Integer id) {
        ServiceEntity serviceEntity = serviceEntityRepository.findByIdIs(id);
        OfferEntity offerEntity = offerEntityRepository.findById(serviceEntity.getOfferId()).get();
        if (MOBILE_SPECIFICATION_ID == offerEntity.getSpecification().getId()) {
            PhoneNumberEntity phoneNumberEntity = phoneNumberEntityRepository.findByServiceId(id);
            phoneNumberEntityRepository.deleteById(phoneNumberEntity.getId());
        }
        Integer balanceId = serviceEntity.getBalance().getId();
        serviceEntityRepository.deleteByIdIs(id);
        balanceEntityRepository.deleteById(balanceId);
    }

    @Override
    public List<InternetServiceOfferModel> getInternetServicesByUserId(Integer id) {
        List<Integer> offerIds = getOfferIdsBySpecification(INTERNET_SPECIFICATION_ID);
        List<ServiceEntity> serviceEntities = serviceEntityRepository.findAllByUserIdAndOfferIdIn(id, offerIds);

        return getInternetServiceOfferModels(serviceEntities);
    }

    @Override
    public Boolean setServiceStatus(ServiceEntity serviceEntity) {
        ServiceEntity service = serviceEntityRepository.findByIdIs(serviceEntity.getId());
        service.setServiceStatus(serviceEntity.getServiceStatus());
        service = serviceEntityRepository.save(service);

        return service.getServiceStatus().getId() == serviceEntity.getServiceStatus().getId();
    }

    @Autowired
    public void setServiceStatusEntityRepository(ServiceStatusEntityRepository serviceStatusEntityRepository) {
        this.serviceStatusEntityRepository = serviceStatusEntityRepository;
    }

    @Autowired
    public void setBalanceEntityRepository(BalanceEntityRepository balanceEntityRepository) {
        this.balanceEntityRepository = balanceEntityRepository;
    }

    @Autowired
    public void setPhoneNumberEntityRepository(PhoneNumberEntityRepository phoneNumberEntityRepository) {
        this.phoneNumberEntityRepository = phoneNumberEntityRepository;
    }

    @Autowired
    public void setOfferEntityRepository(OfferEntityRepository offerEntityRepository) {
        this.offerEntityRepository = offerEntityRepository;
    }

    @Autowired
    public void setAddressEntityRepository(AddressEntityRepository addressEntityRepository) {
        this.addressEntityRepository = addressEntityRepository;
    }

    @Autowired
    public void setSpecificationEntityRepository(SpecificationEntityRepository specificationEntityRepository) {
        this.specificationEntityRepository = specificationEntityRepository;
    }

    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }
}
