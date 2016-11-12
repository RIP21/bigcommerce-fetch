package com.rip21.bigcommerceFetch.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(excludes = ["id"])
@JsonIgnoreProperties(ignoreUnknown = true)
class OptionValue {

    @JsonProperty("option_value_id")
    Long optionValueId

    String label

    String value
}
