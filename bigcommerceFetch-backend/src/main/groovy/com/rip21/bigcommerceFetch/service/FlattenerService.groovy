package com.rip21.bigcommerceFetch.service

import com.rip21.bigcommerceFetch.domain.Category
import com.rip21.bigcommerceFetch.domain.FlatSku
import com.rip21.bigcommerceFetch.domain.Product
import com.rip21.bigcommerceFetch.domain.Sku
import com.rip21.bigcommerceFetch.domain.Value
import org.springframework.stereotype.Service

@Service
class FlattenerService {

    List<FlatSku> flatten(List<Sku> skus, List<Product> products, List<Value> values, List<Category> categories) {
        return skus.collect { sku ->
            return new FlatSku(sku, products.find { product ->
                sku.productId == product.id
            }, values.find { value ->
                sku.options && sku.options[0].optionValueId == value.id
            }, categories.collect { category ->
                if (products.find { product -> sku.productId == product.id }.categories.contains(category.id)) {
                    return category.name
                }
            }
            )
        }
    }
}
