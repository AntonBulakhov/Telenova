package com.telenova.backend.database.repository;

import com.telenova.backend.database.entity.ValueMeasureEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValueMeasureEntityRepository extends CrudRepository<ValueMeasureEntity, Integer> {

}
