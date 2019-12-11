package com.telenova.backend.database.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class UserEntityPK implements Serializable {
    private int id;
    private int roleId;
    private int userStatusId;

    @Column(name = "id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "role_id")
    @Id
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Column(name = "user_status_id")
    @Id
    public int getUserStatusId() {
        return userStatusId;
    }

    public void setUserStatusId(int userStatusId) {
        this.userStatusId = userStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntityPK that = (UserEntityPK) o;

        if (id != that.id) return false;
        if (roleId != that.roleId) return false;
        if (userStatusId != that.userStatusId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + roleId;
        result = 31 * result + userStatusId;
        return result;
    }
}
