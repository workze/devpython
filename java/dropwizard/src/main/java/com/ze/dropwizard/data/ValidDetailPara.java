package com.ze.dropwizard.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.validation.OneOf;
import io.dropwizard.validation.ValidationMethod;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;

/**
 * Created by ZE-C on 2017/11/19.
 */
@Getter
@Setter
public class ValidDetailPara {
    @JsonProperty
    @Max(10)
    private String name;

    @JsonProperty
    @Max(10)
    private int id;

    @JsonProperty
    @OneOf(value = {"private", "public"}, ignoreCase = true, ignoreWhitespace = true)
    String authrity;

    @ValidationMethod(message="name may not be aaa")
    @JsonIgnore
    public boolean isNotCoda() {
        return !"aaa".equals(name);
    }
}
