package com.telenova.backend.charging;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ChargingService {

    @Scheduled(cron = "0 0 0 * * ?")
    public void chargeMoney() {

    }
}
