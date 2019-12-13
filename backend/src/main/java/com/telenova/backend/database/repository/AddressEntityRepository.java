package com.telenova.backend.database.repository;

import com.telenova.backend.database.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressEntityRepository extends CrudRepository<AddressEntity, Integer> {
    AddressEntity findByCityAndStreetAndHouseAndFlat(
            String city,
            String street,
            String house,
            String flat);
}
