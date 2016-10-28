package com.rip21.bigcommerceFetch

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class BigcommerceFetchApplication {

    private static final Logger log = LoggerFactory.getLogger(BigcommerceFetchApplication.class);


    static void main(String[] args) {
        SpringApplication.run BigcommerceFetchApplication, args
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}


