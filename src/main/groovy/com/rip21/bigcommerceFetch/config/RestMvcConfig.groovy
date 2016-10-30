package com.rip21.bigcommerceFetch.config;

import com.rip21.bigcommerceFetch.domain.FlattenedProduct;
import com.rip21.bigcommerceFetch.domain.OptionSet;
import com.rip21.bigcommerceFetch.domain.Product;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RestMvcConfig extends RepositoryRestConfigurerAdapter {

    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(FlattenedProduct.class)
        config.exposeIdsFor(Product.class)
        config.exposeIdsFor(OptionSet.class)
    }

}

