package com.basic.helloworld;

import com.basic.helloworld.db.dao.DaoModule;
import com.basic.helloworld.db.entity.HelloWorldEntity;
import com.google.common.reflect.ClassPath;
import com.google.inject.Stage;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.logging.LoggingFeature;
import ru.vyarus.dropwizard.guice.GuiceBundle;

@Slf4j
public class HelloWorld extends Application<HelloWorldConfiguration> {

  public static void main(String[] args) throws Exception {
    new HelloWorld().run(args);
  }

  @Override
  public void run(HelloWorldConfiguration configuration, Environment environment)
      throws Exception {
    if (configuration.isLoggingEnabled()) {
      environment.jersey().register(new LoggingFeature(
          Logger.getLogger(getClass().getName()), Level.INFO, null, null
      ));
    }

    environment.jersey().register(ClassPath.ResourceInfo.class);

  }

  @Override
  public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
    boolean localConfig = Boolean.parseBoolean(System.getProperty("localConfig", "true"));
    if (localConfig) {
      log.info("Bootstrap from localConfig");
      bootstrap.setConfigurationSourceProvider(
          new SubstitutingSourceProvider(
              bootstrap.getConfigurationSourceProvider(),
              new EnvironmentVariableSubstitutor()));
    }

    bootstrap.addBundle(hibernateBundle);

    GuiceBundle<HelloWorldConfiguration> guiceBundle = GuiceBundle.<HelloWorldConfiguration>builder()
        .modules(new DaoModule(hibernateBundle))
        .enableAutoConfig(getClass().getPackage().getName())
        .build(Stage.PRODUCTION);
    bootstrap.addBundle(guiceBundle);
  }

  private final HibernateBundle<HelloWorldConfiguration> hibernateBundle =
      new HibernateBundle<HelloWorldConfiguration>(HelloWorldEntity.class) {

        public PooledDataSourceFactory getDataSourceFactory(
            HelloWorldConfiguration helloWorldConfiguration) {
          return helloWorldConfiguration.getDatabase();
        }

        @Override
        protected String name() {
          return "HelloWorld";
        }

      };


  @Override
  public String getName() {
    return "helloworld";
  }
}
