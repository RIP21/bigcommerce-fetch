package com.rip21.bigcommerceFetch.domain

import groovy.transform.EqualsAndHashCode
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@EqualsAndHashCode(excludes = ["id"])
@Document(collection = "flattenedProducts")
class FlattenedProduct {

    @Id
    String id

    Long productId

    String productName

    String productPrice

    String productSearchKeywords

    Long optionSetId

    Long optionSetOptionId

    Long optionValueId

    String optionValueLabel

    String optionValue

    Long skuId

    Long skuProductId

    String sku

    String skuPrice

    Long skuProductOptionId

}
