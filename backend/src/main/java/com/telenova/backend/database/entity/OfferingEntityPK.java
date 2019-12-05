package com.telenova.backend.database.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class OfferingEntityPK implements Serializable {
    private int id;
    private int specificationId;

    @Column(name = "id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "specification_id")
    @Id
    public int getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(int specificationId) {
        this.specificationId = specificationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OfferingEntityPK that = (OfferingEntityPK) o;

        if (id != that.id) return false;
        if (specificationId != that.specificationId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + specificationId;
        return result;
    }
}
