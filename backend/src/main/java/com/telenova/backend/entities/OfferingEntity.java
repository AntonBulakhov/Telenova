package com.telenova.backend.entities;

import javax.persistence.*;

@Entity
@Table(name = "offering", schema = "telenovadb", catalog = "")
public class OfferingEntity {
    private int id;
    private String value;
    private OfferingTypeEntity offeringTypeByOfferingTypeId;
    private SpecificationEntity specificationBySpecificationId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OfferingEntity that = (OfferingEntity) o;

        if (id != that.id) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "offering_type_id", referencedColumnName = "id", nullable = false)
    public OfferingTypeEntity getOfferingTypeByOfferingTypeId() {
        return offeringTypeByOfferingTypeId;
    }

    public void setOfferingTypeByOfferingTypeId(OfferingTypeEntity offeringTypeByOfferingTypeId) {
        this.offeringTypeByOfferingTypeId = offeringTypeByOfferingTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "specification_id", referencedColumnName = "id", nullable = false)
    public SpecificationEntity getSpecificationBySpecificationId() {
        return specificationBySpecificationId;
    }

    public void setSpecificationBySpecificationId(SpecificationEntity specificationBySpecificationId) {
        this.specificationBySpecificationId = specificationBySpecificationId;
    }
}
