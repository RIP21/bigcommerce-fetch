package com.rip21.bigcommerceFetch.dao

import com.rip21.bigcommerceFetch.domain.OptionSet
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface OptionSetRepository extends MongoRepository<OptionSet, Long> {

}
