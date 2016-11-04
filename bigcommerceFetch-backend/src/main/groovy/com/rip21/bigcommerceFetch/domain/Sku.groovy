package com.rip21.bigcommerceFetch.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "sku")
class Sku {

    @Id
    Long id

    @JsonProperty("product_id")
    Long productId

    String sku

    String price

    @JsonProperty("is_purchasing_disabled")
    Boolean isPurchasingDisabled

    SkuOptions[] options

}
