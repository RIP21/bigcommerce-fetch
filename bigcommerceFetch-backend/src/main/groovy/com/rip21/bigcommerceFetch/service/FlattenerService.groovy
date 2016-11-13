package com.rip21.bigcommerceFetch.service

import com.rip21.bigcommerceFetch.domain.FlattenedProduct
import com.rip21.bigcommerceFetch.domain.OptionSet
import com.rip21.bigcommerceFetch.domain.Product
import com.rip21.bigcommerceFetch.domain.Sku
import org.springframework.stereotype.Service

@Service
class FlattenerService {

    public List<FlattenedProduct> flatten(List<Product> products, List<OptionSet> optionSets, List<Sku> skus) {
        def productsEntities = new LinkedList<FlattenedProduct>()
        for (product in products) {
            def optionSetsToMap = optionSets.findAll { optionSet -> optionSet.optionSetId == product.optionSetId }
            if (!optionSetsToMap) {
                productsEntities << new FlattenedProduct(
                        productId: product.id,
                        productName: product.name,
                        optionSetId: 0,
                        optionSetOptionId: 0,
                        optionValueId: 0,
                        optionValueLabel: "",
                        optionValue: "")
            } else {
                for (optionSet in optionSetsToMap) {
                    if (optionSet.values.length == 0) {
                        productsEntities << new FlattenedProduct(
                                productId: product.id,
                                productName: product.name,
                                optionSetId: optionSet.optionSetId,
                                optionSetOptionId: optionSet.optionId,
                                optionValueId: 0,
                                optionValueLabel: "",
                                optionValue: ""
                        )
                    } else {
                        for (value in optionSet.values) {
                            productsEntities << new FlattenedProduct(
                                    productId: product.id,
                                    productName: product.name,
                                    optionSetId: optionSet.optionSetId,
                                    optionSetOptionId: optionSet.optionId,
                                    optionValueId: value.optionValueId,
                                    optionValueLabel: value.label,
                                    optionValue: value.value
                            )
                        }
                    }
                }
            }
        }

        productsEntities.each { productEntity ->
            def skuToMap = skus.find { sku ->
                sku.productId == productEntity.productId &&
                        sku.options.length >= 1 ? sku.options.first().optionValueId == productEntity.optionValueId : false
            }
            if (skuToMap) {
                productEntity.skuId = skuToMap.id
                productEntity.skuProductId = skuToMap.productId
                productEntity.sku = skuToMap.sku
                productEntity.skuProductOptionId = skuToMap.options.first().productOptionId
            }

        }

        return productsEntities
    }
}
