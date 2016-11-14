package com.rip21.bigcommerceFetch.service

import com.rip21.bigcommerceFetch.domain.AlternedFlattenedProduct
import com.rip21.bigcommerceFetch.domain.Product
import com.rip21.bigcommerceFetch.domain.Sku
import com.rip21.bigcommerceFetch.domain.Value
import org.springframework.stereotype.Service

@Service
class AlternativeFlattenerService {

    List<AlternedFlattenedProduct> flatten(List<Sku> skus, List<Product> products, List<Value> values) {

        List<AlternedFlattenedProduct> flattened = new LinkedList<>();
        skus.each { sku ->
            products.each { product ->
                if (sku.productId == product.id) {
                    values.each { value ->
                        if (sku.options.length != 0 && sku.options.first().optionValueId == value.id) {
                            flattened << new AlternedFlattenedProduct(sku, product, value)
                        }
                    }
                }
            }
        }

//        flattened = skus.collect {sku ->
//            products.find {product ->
//                sku.productId == product.id}.find{ product ->
//                values.find {value ->
//                    sku.options.length != 0 && sku.options.first().optionValueId == value.id}.collect { value ->
//                    new AlternedFlattenedProduct(sku, product, value) }
//            }
//        }
        return flattened

    }
}
