package com.rip21.bigcommerceFetch.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.EqualsAndHashCode
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "sku")
class Sku {

    @Id
    Long id

    @JsonProperty("product_id")
    Long productId

    String sku

    @JsonProperty("is_purchasing_disabled")
    Boolean isPurchasingDisabled

    SkuOptions[] options

}
