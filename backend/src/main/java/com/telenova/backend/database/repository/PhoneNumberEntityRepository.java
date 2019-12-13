package com.telenova.backend.database.repository;

import com.telenova.backend.database.entity.PhoneNumberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberEntityRepository extends CrudRepository<PhoneNumberEntity, Integer> {
    PhoneNumberEntity findByHoneNumber(String phoneNumber);
    PhoneNumberEntity findByServiceId(Integer id);
}
