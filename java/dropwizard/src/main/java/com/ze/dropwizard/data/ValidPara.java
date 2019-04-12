package com.ze.dropwizard.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * Created by ZE-C on 2017/11/19.
 */
@Getter
@Setter
public class ValidPara {
    @JsonProperty
    @Length(min=1, max=10, message = "123")
    private String name;

    @JsonProperty
    private String id;
}
