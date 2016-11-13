package com.rip21.bigcommerceFetch.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.EqualsAndHashCode
import org.springframework.data.annotation.Id

@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
class OptionValue {

    @Id
    @JsonProperty("option_value_id")
    Long optionValueId

    String label

    String value
}
