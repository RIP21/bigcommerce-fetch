package com.rip21.bigcommerceFetch.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import groovy.transform.EqualsAndHashCode
import org.springframework.data.annotation.Id

@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
class Value {
    @Id
    Long id

    String value

}
