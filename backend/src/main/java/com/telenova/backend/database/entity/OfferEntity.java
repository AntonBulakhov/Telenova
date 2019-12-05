package com.telenova.backend.database.entity;

import javax.persistence.*;

@Entity
@Table(name = "offer", schema = "telenovadb", catalog = "")
@IdClass(OfferEntityPK.class)
public class OfferEntity {
    private int id;
    private int specificationId;
    private int offerStatusId;
    private String name;
    private double price;
    private SpecificationEntity specification;
    private OfferStatusEntity offerStatus;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "specification_id")
    public int getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(int specificationId) {
        this.specificationId = specificationId;
    }

    @Id
    @Column(name = "offer_status_id")
    public int getOfferStatusId() {
        return offerStatusId;
    }

    public void setOfferStatusId(int offerStatusId) {
        this.offerStatusId = offerStatusId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OfferEntity that = (OfferEntity) o;

        if (id != that.id) return false;
        if (specificationId != that.specificationId) return false;
        if (offerStatusId != that.offerStatusId) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + specificationId;
        result = 31 * result + offerStatusId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
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
    @JoinColumn(name = "offer_status_id", referencedColumnName = "id", nullable = false)
    public OfferStatusEntity getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatusEntity offerStatus) {
        this.offerStatus = offerStatus;
    }
}
