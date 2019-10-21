package com.telenova.backend.entities;

import javax.persistence.*;

@Entity
@Table(name = "offer", schema = "telenovadb", catalog = "")
public class OfferEntity {
    private int id;
    private String name;
    private double price;
    private SpecificationEntity specificationBySpecificationId;
    private OfferCategoryEntity offerCategoryByOfferCategoryId;
    private OfferStatusEntity offerStatusByOfferStatusId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (Double.compare(that.price, price) != 0) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "specification_id", referencedColumnName = "id", nullable = false)
    public SpecificationEntity getSpecificationBySpecificationId() {
        return specificationBySpecificationId;
    }

    public void setSpecificationBySpecificationId(SpecificationEntity specificationBySpecificationId) {
        this.specificationBySpecificationId = specificationBySpecificationId;
    }

    @ManyToOne
    @JoinColumn(name = "offer_category_id", referencedColumnName = "id", nullable = false)
    public OfferCategoryEntity getOfferCategoryByOfferCategoryId() {
        return offerCategoryByOfferCategoryId;
    }

    public void setOfferCategoryByOfferCategoryId(OfferCategoryEntity offerCategoryByOfferCategoryId) {
        this.offerCategoryByOfferCategoryId = offerCategoryByOfferCategoryId;
    }

    @ManyToOne
    @JoinColumn(name = "offer_status_id", referencedColumnName = "id", nullable = false)
    public OfferStatusEntity getOfferStatusByOfferStatusId() {
        return offerStatusByOfferStatusId;
    }

    public void setOfferStatusByOfferStatusId(OfferStatusEntity offerStatusByOfferStatusId) {
        this.offerStatusByOfferStatusId = offerStatusByOfferStatusId;
    }
}
