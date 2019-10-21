package com.basic.helloworld.service;

import com.basic.helloworld.HelloWorld;
import com.basic.helloworld.command.HelloWorldCommand;
import com.basic.helloworld.db.entity.HelloWorldEntity;
import com.basic.helloworld.models.HelloWorldModel;
import javax.inject.Inject;

public class HelloWorldService {

  private HelloWorldCommand helloWorldCommand;

  @Inject
  public HelloWorldService(HelloWorldCommand helloWorldCommand) {
    this.helloWorldCommand = helloWorldCommand;
  }


  public HelloWorldModel sayHelloWorld(String firstName, String lastName) {
    helloWorldCommand.createEntity(HelloWorldEntity.builder()
        .firstName(firstName)
        .lastName(lastName)
        .build());
    return HelloWorldModel.builder()
        .firstName(firstName)
        .lastName(lastName)
        .build();
  }

  public HelloWorldModel getHelloWorld(Long id) {
    return toModel(helloWorldCommand.getEntity(id));
  }


  private HelloWorldModel toModel(HelloWorldEntity entity) {
    return HelloWorldModel.builder()
        .firstName(entity.getFirstName())
        .lastName(entity.getLastName())
        .build();
  }

}
