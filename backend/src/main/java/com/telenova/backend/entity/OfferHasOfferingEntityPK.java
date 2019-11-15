package com.telenova.backend.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class OfferHasOfferingEntityPK implements Serializable {
    private int offerId;
    private int offeringId;

    @Column(name = "offer_id")
    @Id
    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    @Column(name = "offering_id")
    @Id
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

        OfferHasOfferingEntityPK that = (OfferHasOfferingEntityPK) o;

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
