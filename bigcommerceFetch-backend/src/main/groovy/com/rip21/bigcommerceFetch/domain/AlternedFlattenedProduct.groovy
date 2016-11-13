package com.rip21.bigcommerceFetch.domain

import groovy.transform.EqualsAndHashCode
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@EqualsAndHashCode(excludes = ["id"])
@Document(collection = "altFlatProducts")
class AlternedFlattenedProduct {

    //SKU

    @Id
    String id

    Long skuid

    Long skuProductId

    String sku

    Boolean isPurchasingDisabled

    Long skuOptionValueId

    Long skuProductOptionId

    //PRODUCT

    Long productId

    String productName

    Boolean isVisible

    //OPTION_VALUE

    Long optionValueId

    String value

}
