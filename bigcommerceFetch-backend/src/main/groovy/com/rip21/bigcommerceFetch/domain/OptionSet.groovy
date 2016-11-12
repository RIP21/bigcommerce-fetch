package com.rip21.bigcommerceFetch.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.EqualsAndHashCode
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@EqualsAndHashCode(excludes = ["id"])
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "optionSets")
public class OptionSet {

    @Id
    Long id;

    @JsonProperty("option_id")
    Long optionId;

    @JsonProperty("option_set_id")
    Long optionSetId;

    @JsonProperty("values")
    OptionValue[] values;

}
