package com.rip21.bigcommerceFetch

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class BigcommerceFetchApplication {

    static void main(String[] args) {
        SpringApplication.run BigcommerceFetchApplication, args
    }

}


