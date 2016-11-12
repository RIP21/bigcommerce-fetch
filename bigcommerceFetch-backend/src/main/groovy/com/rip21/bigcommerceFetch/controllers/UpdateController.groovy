package com.rip21.bigcommerceFetch.controllers

import com.rip21.bigcommerceFetch.service.Scheduler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class UpdateController {

    @Autowired
    Scheduler scheduler

    @RequestMapping("api/update")
    void updateCaches() {
        println "Webhook triggered! ${new Date()}"
        scheduler.fetchData()
    }

}
