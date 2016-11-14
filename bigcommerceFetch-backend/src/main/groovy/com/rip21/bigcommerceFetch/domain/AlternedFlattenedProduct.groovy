package com.rip21.bigcommerceFetch.domain

import groovy.transform.EqualsAndHashCode
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@EqualsAndHashCode(excludes = ["id"])
@Document(collection = "altFlatProducts")
class AlternedFlattenedProduct {

    AlternedFlattenedProduct(Sku sku, Product product, Value value) {
        this.skuid = sku.id
        this.skuProductId = sku.productId
        this.sku = sku.sku
        this.isPurchasingDisabled = sku.isPurchasingDisabled
        this.skuOptionValueId = sku.options.first().optionValueId
        this.skuProductOptionId = sku.options.first().productOptionId

        this.productId = product.id
        this.isVisible = product.isVisible
        this.productName = product.name

        this.optionValueId = value.id
        this.value = value.value
    }

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
