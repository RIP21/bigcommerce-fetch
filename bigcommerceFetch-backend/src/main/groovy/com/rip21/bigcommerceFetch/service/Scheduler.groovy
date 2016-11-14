package com.rip21.bigcommerceFetch.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class Scheduler {

    @Autowired
    SchedulerService schedulerService

    @Scheduled(fixedDelay = 1800000L, initialDelay = 10000L)
    public void fetchData() {

    }


}
