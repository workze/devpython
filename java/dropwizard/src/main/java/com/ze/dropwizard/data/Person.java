package com.ze.dropwizard.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.validation.OneOf;
import io.dropwizard.validation.ValidationMethod;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by ZE-C on 2017/11/19.
 */
@Setter
@Getter
public class Person {
    @JsonProperty
    @NotEmpty
    private String name;

    @NotEmpty
    @JsonProperty
    @OneOf(value = {"m", "f"}, ignoreCase = true, ignoreWhitespace = true)
    private String gender;

    @ValidationMethod(message = "name may not be aaa")
    @JsonIgnore
    public boolean isNotCoda() {
        return !"aaa".equals(name);
    }

}

