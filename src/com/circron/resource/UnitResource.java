package com.circron.resource;

import com.circron.resource.table.Unit;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("unit")
public class UnitResource extends AbstractModelResource<Unit> {
    public UnitResource() { super(Unit.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/byunit")
    public Unit getByUnit(@QueryParam("unit") String id) {
        return super.getEqualsSingle("id", id);
    }
}
