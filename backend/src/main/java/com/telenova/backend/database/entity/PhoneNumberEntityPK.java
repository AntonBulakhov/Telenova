package com.telenova.backend.database.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class PhoneNumberEntityPK implements Serializable {
    private int id;
    private int serviceId;
    private int userId;

    @Column(name = "id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "service_id")
    @Id
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    @Column(name = "user_id")
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumberEntityPK that = (PhoneNumberEntityPK) o;

        if (id != that.id) return false;
        if (serviceId != that.serviceId) return false;
        if (userId != that.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + serviceId;
        result = 31 * result + userId;
        return result;
    }
}
