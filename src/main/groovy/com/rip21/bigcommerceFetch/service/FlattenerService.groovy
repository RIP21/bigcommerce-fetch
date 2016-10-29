package com.rip21.bigcommerceFetch.service

import com.rip21.bigcommerceFetch.domain.OptionSet
import com.rip21.bigcommerceFetch.domain.Product
import com.rip21.bigcommerceFetch.domain.FlattenedProduct
import org.springframework.stereotype.Service

@Service
class FlattenerService {

    public List<FlattenedProduct> flatten(List<Product> products, List<OptionSet> optionSets) {
        def productsEntities = new LinkedList<FlattenedProduct>()
        products.each { product ->
            def optionSetsToMap = optionSets.findAll { optionSet -> optionSet.optionSetId == product.optionSetId }
            optionSetsToMap.each { optionSet ->
                optionSet.values.each { value ->
                    productsEntities.add(new FlattenedProduct(
                            productId: product.id,
                            productPrice: product.price,
                            productSearchKeywords: product.searchKeywords,
                            optionSetId: optionSet.optionSetId,
                            optionSetOptionId: optionSet.optionId,
                            optionValueId: value.optionValueId,
                            optionValueLabel: value.label,
                            optionValue: value.value
                    ))
                }
            }
        }
        return productsEntities
    }
}
