package com.rip21.bigcommerceFetch.controllers

import com.rip21.bigcommerceFetch.service.SchedulerService
import groovy.util.logging.Log4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@Log4j
class UpdateController {

    @Autowired
    SchedulerService schedulerService

    @RequestMapping("api/update")
    void updateCaches() {
        log.info("Webhook triggered!")
        schedulerService.updateItems()
    }

}
