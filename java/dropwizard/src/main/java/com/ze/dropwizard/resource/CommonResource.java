package com.ze.dropwizard.resource;

import com.alibaba.fastjson.JSONObject;
import com.ze.dropwizard.data.Animal;
import com.ze.dropwizard.data.Person;
import com.ze.hk2.AutoConfigBundle;
import io.dropwizard.jersey.caching.CacheControl;
import io.dropwizard.jersey.params.IntParam;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.hk2.api.Self;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.TimeUnit;

//import org.jvnet.hk2.annotations.;

/**
 * Created by ZE-C on 2017/11/19.
 */
@Path("/common")
@Api("common")
@Produces(MediaType.APPLICATION_JSON)
@Service
public class CommonResource {

    private String _id = "123";
    private static final Logger log = LoggerFactory.getLogger(CommonResource.class);

    @Inject
    ValidateResource validateResource;

    public CommonResource() {
//        System.out.println(validateResource.getName());
    }

    @GET
    public JSONObject sayHello() {
        log.info("hi,i am info");
        log.debug("debug");
        log.warn("warn");
//        log.debug("hi,i am debug");
//        System.out.println(validateResource.getName());
        JSONObject res = new JSONObject();
        res.put("name", "common resource");
        return res;
    }

    @GET
    @Path("/get/{id}")
    @CacheControl(maxAge = 6, maxAgeUnit = TimeUnit.SECONDS)
    public String  sayHello2(@PathParam("id")IntParam id) {
        log.info("hi,i am info");
        log.debug("debug");
        log.warn("warn");
//        log.debug("hi,i am debug");
//        System.out.println(validateResource.getName());
        JSONObject res = new JSONObject();
        res.put("name", "common resource");
        return id.toString();
    }

    @POST
    @Path("person")
    public Response createPerson(@Valid Person person) {
        System.out.println();
        System.out.println("person in");
        return Response.ok(person).status(200).build();
    }

    @POST
    @Path("animal")
    public Response createPerson2(Animal para) {

        System.out.println("person in");
        System.out.println();
        return Response.ok(para).status(200).build();
    }

    @PostConstruct
    public void init() {
//        System.out.println(bookService.getBook());
    }

}
