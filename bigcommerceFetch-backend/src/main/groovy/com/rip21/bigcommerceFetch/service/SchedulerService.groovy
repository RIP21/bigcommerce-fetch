package com.rip21.bigcommerceFetch.service

import com.rip21.bigcommerceFetch.dao.FlattenedProductRepository
import com.rip21.bigcommerceFetch.dao.OptionSetRepository
import com.rip21.bigcommerceFetch.dao.ProductRepository
import com.rip21.bigcommerceFetch.dao.SkuRepository
import com.rip21.bigcommerceFetch.domain.OptionSet
import com.rip21.bigcommerceFetch.domain.Product
import com.rip21.bigcommerceFetch.domain.Sku
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SchedulerService {

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

    public List fetchData() {
        def products
        def optionSets
        def skus
        def refetchedObjects
        def flattenedProducts
        println("Data starts to fetch ${new Date()}")
        try {
            products = fetchService.fetchAllAvailableProducts()
            optionSets = fetchService.fetchOptionSets(products)
            skus = fetchService.fetchNonDisabledSkis()
            refetchedObjects = fetchService.refetch()

            (products, optionSets, skus) = addRefetchedItems(products, optionSets, skus, refetchedObjects)

            flattenedProducts = flattenerService.flatten(products, optionSets, skus)

            println("Products: ${optionSets.size()} OptionSets: ${skus.size()} SKUs: ${products.size()} Flat Products: ${flattenedProducts.size()}")

            if (products.size() > 0 && optionSets.size() > 0 && skus.size() > 0 && flattenedProducts.size() > 0) {
                [flattenedProductRepository, productRepository, productRepository, skuRepository]*.deleteAll()

                productRepository.save(products)
                optionSetRepository.save(optionSets)
                skuRepository.save(skus)
                flattenedProductRepository.save(flattenedProducts)

                println("Data fetched succesfully  ${new Date()}")
            }
        } catch (Exception e) {
            println("Error when update cache! ${new Date()}")
            e.printStackTrace()
        }
        [products, optionSets, skus, refetchedObjects, flattenedProducts]
    }

    public List addRefetchedItems(List<Product> products, List<OptionSet> optionSets, List<Sku> skus, List<Object> refetchedObjects) {
        products += refetchedObjects.findAll { it instanceof Product }
        optionSets += refetchedObjects.findAll { it instanceof OptionSet }
        skus += refetchedObjects.findAll { it instanceof Sku }
        [products, optionSets, skus]
    }
}
