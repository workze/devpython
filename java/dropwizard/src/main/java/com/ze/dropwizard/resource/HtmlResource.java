package com.ze.dropwizard.resource;

import org.jvnet.hk2.annotations.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by ZE-C on 2017/11/19.
 */
@Path("/html")
//@Produces(MediaType.TEXT_XML)
@Produces(MediaType.TEXT_HTML)
@Service
public class HtmlResource {
    @GET
    @Path("/h")
    public String sayHello() {
        return "<html>   <body><h1>hi, I am html.</h1></body> </html>";
    }

    @GET
    @Path("/a/{para:.+}")
    public String sayHello2(@PathParam("para") String car) {
        System.out.println(car);
        return car;
    }
}
