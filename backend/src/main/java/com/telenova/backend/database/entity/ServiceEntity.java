package com.telenova.backend.database.entity;

import javax.persistence.*;

@Entity
@Table(name = "service", schema = "telenovadb", catalog = "")
@IdClass(ServiceEntityPK.class)
public class ServiceEntity {
    private int id;
    private int userId;
    private int offerId;
    private BalanceEntity balance;
    private AddressEntity address;
    private ServiceStatusEntity service;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "offer_id")
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

        ServiceEntity that = (ServiceEntity) o;

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

    @ManyToOne
    @JoinColumn(name = "balance_id", referencedColumnName = "id", nullable = false)
    public BalanceEntity getBalance() {
        return balance;
    }

    public void setBalance(BalanceEntity balance) {
        this.balance = balance;
    }

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    @ManyToOne
    @JoinColumn(name = "service_status_id", referencedColumnName = "id", nullable = false)
    public ServiceStatusEntity getService() {
        return service;
    }

    public void setService(ServiceStatusEntity service) {
        this.service = service;
    }
}
