package com.rip21.bigcommerceFetch.dao

import com.rip21.bigcommerceFetch.domain.FlatSku
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(exported = true)
interface FlatSkuRepository extends MongoRepository<FlatSku, Long> {
}
