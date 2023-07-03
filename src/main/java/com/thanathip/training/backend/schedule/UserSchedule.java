package com.thanathip.training.backend.schedule;

import com.thanathip.training.backend.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserSchedule {


    //1 => second
    //2 => minute
    //3 => hour
    //4 => day
    //5 => month
    //6 => year

    /**
     * 0 0 0 * * ? => ทุกๆ วันเวลา 00:00:00
     */
    @Scheduled(cron="0 0 0 * * ?",zone = "Asia/Bangkok")
    public void TestsEveryDay() {
        log.info("Test every day");
    }

}
