package com.basic.helloworld.db.dao;


import com.basic.helloworld.db.entity.HelloWorldEntity;
import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.hibernate.UnitOfWork;
import javax.inject.Inject;
import org.hibernate.SessionFactory;

public class HelloWorldDao extends AbstractDAO<HelloWorldEntity> {

    @Inject
    public HelloWorldDao(SessionFactory sessionFactory) {
      super(sessionFactory);
    }

    @UnitOfWork
    public HelloWorldEntity findById(Long id) {
      return get(id);
    }

    @UnitOfWork
    public long create(HelloWorldEntity helloWorldEntity) {
      return persist(helloWorldEntity).getInternalId();
    }

}

