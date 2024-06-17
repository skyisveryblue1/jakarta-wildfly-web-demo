package sample.org.test;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;

import jakarta.ejb.Stateless;
import jakarta.annotation.Resource;
import jakarta.ws.rs.core.MediaType;


@ApplicationPath("/api")
public class HelloApplication extends Application {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return "Hello from EJB";
    }
}