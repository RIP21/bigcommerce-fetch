package com.rip21.bigcommerceFetch.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
class SkuOptions {

    @JsonProperty("product_option_id")
    Long productOptionId

    @JsonProperty("option_value_id")
    Long optionValueId

}
