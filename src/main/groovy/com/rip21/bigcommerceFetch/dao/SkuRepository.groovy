package com.rip21.bigcommerceFetch.dao

import com.rip21.bigcommerceFetch.domain.Sku
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SkuRepository extends MongoRepository<Sku, Long> {

}
