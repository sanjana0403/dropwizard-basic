package com.basic.helloworld.command;

import com.basic.helloworld.db.dao.HelloWorldDao;
import com.basic.helloworld.db.entity.HelloWorldEntity;
import io.dropwizard.hibernate.UnitOfWork;
import javax.inject.Inject;

public class HelloWorldCommand {

  private HelloWorldDao helloWorldDao;

  @Inject
  public HelloWorldCommand(HelloWorldDao helloWorldDao) {
    this.helloWorldDao = helloWorldDao;
  }

  @UnitOfWork
  public HelloWorldEntity getEntity(Long id) {
    return helloWorldDao.findById(id);
  }

  @UnitOfWork
  public void createEntity(HelloWorldEntity entity) {
    helloWorldDao.create(entity);
  }
}
