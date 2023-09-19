package com.circron.resource;

import com.circron.resource.table.CircuitType;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("circuit_type")
public class CircuitTypeResource extends AbstractModelResource<CircuitType> {
    public CircuitTypeResource() { super(CircuitType.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/byname")
    public CircuitType getByCircuit(@QueryParam("name") String name) {
        return super.getEqualsSingle("name", name);
    }
}
