package com.ze.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import lombok.Getter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by ZE-C on 2017/11/19.
 */
@Getter
public class AppConfig extends Configuration {
    @JsonProperty
    @NotEmpty
    private String appName = "test";

    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;

    @JsonProperty
    public DataSourceFactory database = new DataSourceFactory();
}
