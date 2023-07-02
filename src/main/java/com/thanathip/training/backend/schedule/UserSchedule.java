package com.thanathip.training.backend.schedule;

import com.thanathip.training.backend.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserSchedule {

    private final UserService userService;

    public UserSchedule(UserService userService) {
        this.userService = userService;
    }


    //1 => second
    //2 => minute
    //3 => hour
    //4 => day
    //5 => month
    //6 => year

    /**
     * run every 5 second
     */
    @Scheduled(cron = "*/5 * * * * *",zone = "Asia/Bangkok")
    public void TestsEvery5Second() {
        log.info("Test every 5 second");
    }
    /**
     * run every minute
     */
    @Scheduled(cron="0 * * * * *",zone = "Asia/Bangkok")
    public void TestsEveryMinute() {
        log.info("Test every minute");
    }

    /**
     * 0 0 0 * * ? => ทุกๆ วันเวลา 00:00:00
     */
    @Scheduled(cron="0 0 0 * * *",zone = "Asia/Bangkok")
    public void clearUser() {
        log.info("Clear user");
    }

    /**
     * Every day at 09:00
     */
    @Scheduled(cron="0 0 9 * * *",zone = "Asia/Bangkok")
    public void clearUser2() {
        log.info("Clear user2");
    }

    /**
     * Every day at 10:50
     */
    @Scheduled(cron="0 50 10 * * *",zone = "Asia/Bangkok")
    public void clearUser3() {
        log.info("Clear user3");
    }

}
