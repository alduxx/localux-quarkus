package br.com.bb.localux;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "oi";
    }

    @Inject
    LightsService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/light/{lightId}/status")
    public String lightStatus(@PathParam int lightId){
        return service.lightStatus(lightId);
    }

}