package com.rip21.bigcommerceFetch.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

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

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        FlattenedProduct that = (FlattenedProduct) o

        if (optionSetId != that.optionSetId) return false
        if (optionSetOptionId != that.optionSetOptionId) return false
        if (optionValue != that.optionValue) return false
        if (optionValueId != that.optionValueId) return false
        if (optionValueLabel != that.optionValueLabel) return false
        if (productId != that.productId) return false
        if (productName != that.productName) return false
        if (productPrice != that.productPrice) return false
        if (productSearchKeywords != that.productSearchKeywords) return false
        if (sku != that.sku) return false
        if (skuId != that.skuId) return false
        if (skuPrice != that.skuPrice) return false
        if (skuProductId != that.skuProductId) return false
        if (skuProductOptionId != that.skuProductOptionId) return false

        return true
    }

    int hashCode() {
        int result
        result = (productId != null ? productId.hashCode() : 0)
        result = 31 * result + (productName != null ? productName.hashCode() : 0)
        result = 31 * result + (productPrice != null ? productPrice.hashCode() : 0)
        result = 31 * result + (productSearchKeywords != null ? productSearchKeywords.hashCode() : 0)
        result = 31 * result + (optionSetId != null ? optionSetId.hashCode() : 0)
        result = 31 * result + (optionSetOptionId != null ? optionSetOptionId.hashCode() : 0)
        result = 31 * result + (optionValueId != null ? optionValueId.hashCode() : 0)
        result = 31 * result + (optionValueLabel != null ? optionValueLabel.hashCode() : 0)
        result = 31 * result + (optionValue != null ? optionValue.hashCode() : 0)
        result = 31 * result + (skuId != null ? skuId.hashCode() : 0)
        result = 31 * result + (skuProductId != null ? skuProductId.hashCode() : 0)
        result = 31 * result + (sku != null ? sku.hashCode() : 0)
        result = 31 * result + (skuPrice != null ? skuPrice.hashCode() : 0)
        result = 31 * result + (skuProductOptionId != null ? skuProductOptionId.hashCode() : 0)
        return result
    }
}
