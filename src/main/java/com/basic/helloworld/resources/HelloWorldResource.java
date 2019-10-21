package com.basic.helloworld.resources;

import com.basic.helloworld.models.HelloWorldModel;
import com.basic.helloworld.service.HelloWorldService;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/helloWorld")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class HelloWorldResource {

  private final HelloWorldService helloWorldService;

  @Inject
  public HelloWorldResource(HelloWorldService helloWorldService) {
    this.helloWorldService = helloWorldService;
  }

  @POST
  public HelloWorldModel sayHello(@QueryParam("firstName") String firstName,
      @QueryParam("lastName") String lastName) {
    return helloWorldService.sayHelloWorld(firstName, lastName);
  }

  @GET
  public HelloWorldModel getHello(@QueryParam("id") Long id) {
    return helloWorldService.getHelloWorld(id);
  }
}
