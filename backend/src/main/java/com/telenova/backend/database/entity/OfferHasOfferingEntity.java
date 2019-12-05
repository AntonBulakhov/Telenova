package com.telenova.backend.database.entity;

import javax.persistence.*;

@Entity
@Table(name = "offer_has_offering", schema = "telenovadb", catalog = "")
@IdClass(OfferHasOfferingEntityPK.class)
public class OfferHasOfferingEntity {
    private int offerId;
    private int offeringId;

    @Id
    @Column(name = "offer_id")
    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    @Id
    @Column(name = "offering_id")
    public int getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(int offeringId) {
        this.offeringId = offeringId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OfferHasOfferingEntity that = (OfferHasOfferingEntity) o;

        if (offerId != that.offerId) return false;
        if (offeringId != that.offeringId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = offerId;
        result = 31 * result + offeringId;
        return result;
    }
}
