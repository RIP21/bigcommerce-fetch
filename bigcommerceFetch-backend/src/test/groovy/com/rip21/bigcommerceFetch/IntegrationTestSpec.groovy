package com.rip21.bigcommerceFetch

import com.rip21.bigcommerceFetch.dao.FlattenedProductRepository
import com.rip21.bigcommerceFetch.dao.OptionSetRepository
import com.rip21.bigcommerceFetch.dao.ProductRepository
import com.rip21.bigcommerceFetch.dao.SkuRepository
import com.rip21.bigcommerceFetch.domain.OptionSet
import com.rip21.bigcommerceFetch.domain.Product
import com.rip21.bigcommerceFetch.domain.Sku
import com.rip21.bigcommerceFetch.service.FetchService
import com.rip21.bigcommerceFetch.service.FlattenerService
import com.rip21.bigcommerceFetch.service.SchedulerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Ignore
import spock.lang.Specification

@SpringBootTest
class IntegrationTestSpec extends Specification {

    @Autowired
    FetchService fetchService

    @Autowired
    FlattenerService flattenerService

    @Autowired
    ProductRepository productRepository

    @Autowired
    OptionSetRepository optionSetRepository

    @Autowired
    SkuRepository skuRepository

    @Autowired
    FlattenedProductRepository flattenedProductRepository

    @Autowired
    SchedulerService schedulerService

    @Ignore
    def "Fetches all products and option sets, flatten them and persist them all on MongoDB"() {
        when:
            def (products, optionSets, skus, _, flattenedProducts) = schedulerService.fetchData()
        then:
            products == productRepository.findAll()
            optionSets == optionSetRepository.findAll()
            skus == skuRepository.findAll()
            flattenedProducts == flattenedProductRepository.findAll()

            products.size() > 0
            optionSets.size() > 0
            skus.size() > 0
            flattenedProducts.size() > 0

            productRepository.findAll().size() > 0
            optionSetRepository.findAll().size() > 0
            skuRepository.findAll().size() > 0
            flattenedProductRepository.findAll().size() > 0
    }

    def "Refetch should work fine"() {
        given:
            def linksToRefetch = [
                    "https://store-2e83t.mybigcommerce.com/api/v2/products.json?limit=10&is_visible=true&availability=available&page=1",
                    "https://store-2e83t.mybigcommerce.com/api/v2/optionsets/148/options.json",
                    "https://store-2e83t.mybigcommerce.com/api/v2/products/skus.json?limit=10&page=1"
            ]
            def products = [new Product()]
            def optionSets = [new OptionSet()]
            def skus = [new Sku()]
        when:
            def result = fetchService.refetch(linksToRefetch)
            (products, optionSets, skus) = schedulerService.addRefetchedItems(products, optionSets, skus, result)
        then:
            result.size() == 21
            products.size() == 11
            optionSets.size() == 2
            skus.size() == 11

    }

}
