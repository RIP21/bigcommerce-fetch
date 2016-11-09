package com.rip21.bigcommerceFetch.config

import com.rip21.bigcommerceFetch.domain.FlattenedProduct
import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter

@Configuration
public class RestMvcConfig extends RepositoryRestConfigurerAdapter {

    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(FlattenedProduct.class)
        config.setDefaultPageSize(100000)
        config.setMaxPageSize(100000)
        config.setBasePath("/api")
    }

}

