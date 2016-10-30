package com.rip21.bigcommerceFetch.dao

import com.rip21.bigcommerceFetch.domain.FlattenedProduct
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.rest.core.annotation.RestResource

@RestResource
interface FlattenedProductRepository extends MongoRepository<FlattenedProduct, Long> {
}
