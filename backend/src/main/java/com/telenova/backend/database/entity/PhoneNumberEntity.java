package com.telenova.backend.database.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "phone_number", schema = "telenovadb", catalog = "")
@IdClass(PhoneNumberEntityPK.class)
public class PhoneNumberEntity {
    private int id;
    private int serviceId;
    private int userId;
    private String honeNumber;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "service_id")
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "hone_number")
    public String getHoneNumber() {
        return honeNumber;
    }

    public void setHoneNumber(String honeNumber) {
        this.honeNumber = honeNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumberEntity that = (PhoneNumberEntity) o;

        if (id != that.id) return false;
        if (serviceId != that.serviceId) return false;
        if (userId != that.userId) return false;
        if (honeNumber != null ? !honeNumber.equals(that.honeNumber) : that.honeNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + serviceId;
        result = 31 * result + userId;
        result = 31 * result + (honeNumber != null ? honeNumber.hashCode() : 0);
        return result;
    }
}
