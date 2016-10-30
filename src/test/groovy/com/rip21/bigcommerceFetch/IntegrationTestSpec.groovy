package com.rip21.bigcommerceFetch

import com.rip21.bigcommerceFetch.dao.FlattenedProductRepository
import com.rip21.bigcommerceFetch.dao.OptionSetRepository
import com.rip21.bigcommerceFetch.dao.ProductRepository
import com.rip21.bigcommerceFetch.dao.SkuRepository
import com.rip21.bigcommerceFetch.service.FetchService
import com.rip21.bigcommerceFetch.service.FlattenerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
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

    void "Fetches all products and option sets, flatten them and persist them all on MongoDB"() {
        when:
            flattenedProductRepository.deleteAll()
            productRepository.deleteAll()
            optionSetRepository.deleteAll()
            skuRepository.deleteAll()

            def products = fetchService.fetchAllAvailableProducts()
            def optionSets = fetchService.fetchOptionSets(products)
            def skus = fetchService.fetchNonDisabledSkis()

            productRepository.save(products)
            optionSetRepository.save(optionSets)
            skuRepository.save(skus)

            /*def products =  productRepository.findAll()
            def optionSets = optionSetRepository.findAll()
            def skus = skuRepository.findAll()*/

            def flattenedProducts = flattenerService.flatten(products, optionSets, skus)
            flattenedProductRepository.save(flattenedProducts)
        then:

            flattenedProducts == flattenedProductRepository.findAll()

            products.size() > 0
            optionSets.size() > 0
            flattenedProducts.size() > 0

            productRepository.findAll().size() > 0
            optionSetRepository.findAll().size() > 0
            flattenedProductRepository.findAll().size() > 0
    }

}
