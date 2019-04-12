package com.ze.dropwizard.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by ZE-C on 2017/11/19.
 */
@Getter
@Setter
public class Animal {
    @JsonProperty
    private String name;

    @JsonProperty
    private String id;
}
