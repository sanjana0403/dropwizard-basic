package com.basic.helloworld.db.dao;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;

@Slf4j
public class DaoModule extends AbstractModule {

  private HibernateBundle hibernateBundle;

  public DaoModule(HibernateBundle hibernateBundle) {
    this.hibernateBundle = hibernateBundle;
  }

  @Override
  protected void configure() {
    //NOOP
  }

  @Provides
  @Singleton
  HelloWorldDao provideHelloWorldDao() {
    return new UnitOfWorkAwareProxyFactory(hibernateBundle)
        .create(HelloWorldDao.class, SessionFactory.class, this.hibernateBundle.getSessionFactory());
  }


}
