package com.ze.dropwizard.resource;

import com.alibaba.fastjson.JSONObject;
import com.ze.dropwizard.data.Authority;
import com.ze.dropwizard.data.ValidDetailPara;
import com.ze.dropwizard.data.ValidPara;
import io.dropwizard.jersey.params.IntParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.jvnet.hk2.annotations.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by ZE-C on 2017/11/19.
 */
@Path("/valid")
@Api("validate learning")
@Produces(MediaType.APPLICATION_JSON)
@Service
public class ValidateResource {

    public ValidateResource(){

    }

    public String getName(){
        return "validresource";
    }

    @GET
    @Path("/notempty")
    @ApiOperation("NotEmpty")
    public JSONObject valid(@QueryParam("name") @NotEmpty String name) {
        JSONObject res = new JSONObject();
        res.put("data",name);
        return res;
    }

    @GET
    @Path("/email")
    @ApiOperation("Email")
    public JSONObject validEmail(@QueryParam("email") @NotEmpty @Length(max = 1) String name) {
        JSONObject res = new JSONObject();
        res.put("data",name);
        return res;
    }

    @POST
    @Path("/valid")
    @ApiOperation("Valid")
    public JSONObject valid1( @NotNull @Valid ValidPara para) {
        JSONObject res = new JSONObject();
        res.put("data",para);
        return res;
    }

    @POST
    @Path("/valid_detail")
    @ApiOperation("valid_detail")
    public JSONObject valid2( @NotNull @Valid ValidDetailPara para) {
        JSONObject res = new JSONObject();
        res.put("data",para);
        return res;
    }

    @POST
    @Path("/intparam")
    @ApiOperation("intparam")
    public JSONObject validintparm(@QueryParam("intparam") IntParam para) {
        JSONObject res = new JSONObject();
        res.put("data",para);
        return res;
    }

    @POST
    @Path("/enum")
    @ApiOperation("enum")
    public JSONObject enumvalid(@QueryParam("authority") Authority para) {
        JSONObject res = new JSONObject();
        res.put("data",para);
        return res;
    }



}
