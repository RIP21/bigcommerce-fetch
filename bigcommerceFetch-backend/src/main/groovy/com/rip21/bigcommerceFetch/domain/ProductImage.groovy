package com.rip21.bigcommerceFetch.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
class ProductImage {

    @JsonProperty("tiny_url")
    String tinyImg

    @JsonProperty("standard_url")
    String standardImg

}
