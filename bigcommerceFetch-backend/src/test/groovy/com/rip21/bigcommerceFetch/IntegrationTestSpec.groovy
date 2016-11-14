package com.rip21.bigcommerceFetch

import com.rip21.bigcommerceFetch.dao.FlatSkuRepository
import com.rip21.bigcommerceFetch.dao.ProductRepository
import com.rip21.bigcommerceFetch.dao.SkuRepository
import com.rip21.bigcommerceFetch.dao.ValueRepository
import com.rip21.bigcommerceFetch.domain.Product
import com.rip21.bigcommerceFetch.domain.Sku
import com.rip21.bigcommerceFetch.domain.Value
import com.rip21.bigcommerceFetch.service.FilteringService
import com.rip21.bigcommerceFetch.service.FlattenerService
import com.rip21.bigcommerceFetch.service.GenericFetchService
import com.rip21.bigcommerceFetch.service.SchedulerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
@SpringBootTest
class IntegrationTestSpec extends Specification {

    @Autowired
    GenericFetchService genericFetchService

    @Autowired
    FlattenerService flattenerService

    @Autowired
    SchedulerService schedulerService

    @Autowired
    FilteringService filteringService

    @Autowired
    ProductRepository productRepository

    @Autowired
    SkuRepository skuRepository

    @Autowired
    ValueRepository valueRepository

    @Autowired
    FlatSkuRepository flatSkuRepository

    def "GenericFetchService works fine"() {
        setup:
            [flatSkuRepository, productRepository, skuRepository, valueRepository]*.deleteAll()
        when:
            def skus = genericFetchService.fetch(Sku.class)
            def products = genericFetchService.fetch(Product.class)
            def values = genericFetchService.fetch(Value.class)

        then:
            skus.size() > 0
            products.size() > 0
            values.size() > 0

            skus.first() instanceof Sku
            products.first() instanceof Product
            values.first() instanceof Value
        and:
            productRepository.save(products)
            skuRepository.save(skus)
            valueRepository.save(values)
    }

    def "Flattener works as expected"() {
        setup:
            def skus = skuRepository.findAll()
            def products = productRepository.findAll()
            def values = valueRepository.findAll()
        when:
            def result = flattenerService.flatten(skus, products, values)
        then: "Amount of flattenedProducts must be the same as SKUs"
            result.size() == skus.size()
        and:
            flatSkuRepository.save(result)
    }

    def "Filtering works properly"() {
        when:
            def result = filteringService.filterInvalidRecords(flatSkuRepository.findAll())
        then:
            result.findAll {
                !(it.skuOptionValueId && it.productId && !it.isPurchasingDisabled && it.isVisible)
            }.size() == 0
        and:
            flatSkuRepository.save(result)
    }

    def "Refetch method works as expected"() {
        given: "Two failed links"
            genericFetchService.failedLinks = ["https://store-2e83t.mybigcommerce.com/api/v2/products.json?limit=10&page=1",
                                               "https://store-2e83t.mybigcommerce.com/api/v2/products.json?limit=10&page=2"]
        when: "Called refetch with proper class for the links"
            def result = genericFetchService.refetch(Product.class)
        then: "Refetch 20 items as expected"
            result.size() == 20
    }

}
