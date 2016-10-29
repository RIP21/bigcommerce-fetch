package com.rip21.bigcommerceFetch

import com.rip21.bigcommerceFetch.service.FetchService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class FetchServiceSpec extends Specification {

    @Autowired
    FetchService fetchService;

    void "Fetches all products and option sets"() {
        when:
            def products = fetchService.fetchProducts()
            def optionSets = fetchService.fetchOptionSets(products)
        expect:
            products.size() > 0
            optionSets.size() > 0
    }

}
