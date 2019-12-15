package com.telenova.backend.web.dto;

public class UserWithSumDto {
    private SafeUser user;
    private float sum;

    public SafeUser getUser() {
        return user;
    }

    public void setUser(SafeUser user) {
        this.user = user;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }
}
