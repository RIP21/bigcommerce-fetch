package com.rip21.bigcommerceFetch.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.EqualsAndHashCode
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@EqualsAndHashCode(excludes = ["id"])
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "categories")
class Category {

    @Id
    Long id

    @JsonProperty("parent_id")
    Long parentId

    String name

}
