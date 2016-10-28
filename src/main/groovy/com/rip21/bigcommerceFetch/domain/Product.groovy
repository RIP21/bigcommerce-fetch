package com.rip21.bigcommerceFetch.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class Product {

    Long id
    String price

    @JsonProperty("search_keywords")
    String searchKeywords

    @JsonProperty("option_set_id")
    String optionSetId

    @JsonProperty("is_visible")
    Boolean isVisible;
}
