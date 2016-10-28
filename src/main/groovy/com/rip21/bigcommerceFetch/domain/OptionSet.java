package com.rip21.bigcommerceFetch.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OptionSet {

    Long id;

    @JsonProperty("option_id")
    Long optionId;

    @JsonProperty("option_set_id")
    Long optionSetId;

    List<OptionValue> values;

}
