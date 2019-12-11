package com.telenova.backend.database.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "offering", schema = "telenovadb", catalog = "")
public class OfferingEntity {
    private int id;
    private String value;
    private SpecificationEntity specification;
    private OfferingTypeEntity offeringType;
    private ValueMeasureEntity valueMeasure;

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
    @JoinColumn(name = "specification_id", referencedColumnName = "id", nullable = false)
    public SpecificationEntity getSpecification() {
        return specification;
    }

    public void setSpecification(SpecificationEntity specification) {
        this.specification = specification;
    }

    @ManyToOne
    @JoinColumn(name = "offering_type_id", referencedColumnName = "id", nullable = false)
    public OfferingTypeEntity getOfferingType() {
        return offeringType;
    }

    public void setOfferingType(OfferingTypeEntity offeringType) {
        this.offeringType = offeringType;
    }

    @ManyToOne
    @JoinColumn(name = "value_measure_id", referencedColumnName = "id", nullable = false)
    public ValueMeasureEntity getValueMeasure() {
        return valueMeasure;
    }

    public void setValueMeasure(ValueMeasureEntity valueMeasure) {
        this.valueMeasure = valueMeasure;
    }
}
