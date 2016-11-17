package com.rip21.bigcommerceFetch.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.EqualsAndHashCode
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@EqualsAndHashCode(excludes = ["id"])
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "products")
class Product {

    @Id
    Long id

    String name

    @JsonProperty("option_set_id")
    Long optionSetId

    @JsonProperty("is_visible")
    Boolean isVisible

    @JsonProperty("primary_image")
    ProductImage productImage


}
