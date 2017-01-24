package com.rip21.bigcommerceFetch.service

import com.rip21.bigcommerceFetch.domain.FlatSku
import com.rip21.bigcommerceFetch.domain.Product
import com.rip21.bigcommerceFetch.domain.Sku
import com.rip21.bigcommerceFetch.domain.Value
import org.springframework.stereotype.Service

@Service
class FlattenerService {

    List<FlatSku> flatten(List<Sku> skus, List<Product> products, List<Value> values) {
        return skus.collect { sku ->
            return new FlatSku(sku, products.find { product ->
                sku.productId == product.id
            }, values.find { value ->
                sku.options && sku.options[0].optionValueId == value.id
            })
        }
    }
}
