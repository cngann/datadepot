package com.circron.resource;

import com.circron.resource.table.Circuit;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("circuit")
public class CircuitResource extends AbstractModelResource<Circuit> {
    public CircuitResource() { super(Circuit.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/bycircuit")
    public Circuit getByCircuit(@QueryParam("circuit") String id) {
        return super.getEqualsSingle("id", id);
    }
}
