package com.rip21.bigcommerceFetch.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "flattenedProducts")
class FlattenedProduct {
    @Id
    Long id

    Long productId

    String productPrice

    String productSearchKeywords

    String optionSetId

    Long optionSetOptionId

    Long optionValueId

    String optionValueLabel

    String optionValue

}
