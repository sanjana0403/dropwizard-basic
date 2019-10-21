package com.basic.helloworld;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
public class HelloWorldConfiguration extends Configuration {

  @NotEmpty
  @NotNull
  private String name;

  private boolean loggingEnabled = false;

  @NotNull
  private DataSourceFactory database = new DataSourceFactory();

}
