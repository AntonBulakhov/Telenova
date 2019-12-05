package com.telenova.backend.database.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ServiceEntityPK implements Serializable {
    private int id;
    private int userId;
    private int offerId;

    @Column(name = "id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "user_id")
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "offer_id")
    @Id
    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceEntityPK that = (ServiceEntityPK) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (offerId != that.offerId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + offerId;
        return result;
    }
}
