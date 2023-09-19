package com.circron.resource;

import com.circron.resource.table.CircuitMeta;
import com.sun.jersey.api.core.ResourceContext;
import com.sun.jersey.spi.resource.Singleton;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

@Singleton
@Path("circuit_meta")
public class CircuitMetaResource extends AbstractModelResource<CircuitMeta> {
    @Context
    private ResourceContext _resourceContext;

    public CircuitMetaResource() { super(CircuitMeta.class); }

    @GET
    @Path("/bycircuitid")
    public List<CircuitMeta> getByCircuitId(@QueryParam("circuit") Long id) {
        return super.getEquals("circuitId", id);
    }
}
