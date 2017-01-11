package com.rip21.bigcommerceFetch.domain

import groovy.transform.EqualsAndHashCode
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@EqualsAndHashCode(excludes = ["id"])
@Document(collection = "flatSkus")

class FlatSku {

    FlatSku() {

    }

    FlatSku(Sku sku, Product product, Value value, List<String> categories) {
        this.id = sku.id
        this.skuid = sku.id
        this.skuProductId = sku.productId
        this.sku = sku.sku
        this.isPurchasingDisabled = sku.isPurchasingDisabled
        if (sku.options && product && value) {
            this.skuOptionValueId = sku.options[0].optionValueId
            this.skuProductOptionId = sku.options[0].productOptionId

        this.productId = product.id
        this.isVisible = product.isVisible
        this.productName = product.name
            this.tinyImg = product?.productImage?.tinyImg
            this.standardImg = product?.productImage?.standardImg

        this.optionValueId = value.id
        this.value = value.value
        }
        if (categories) {
            this.categories = categories
        }
    }

    //SKU

    @Id
    Long id

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

    String tinyImg

    String standardImg

    //OPTION_VALUE

    Long optionValueId

    String value

    String[] categories

}
