package com.circron.resource;

import com.circron.resource.table.UnitType;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("unit_type")
public class UnitTypeResource extends AbstractModelResource<UnitType> {
    public UnitTypeResource() { super(UnitType.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/byname")
    public UnitType getByUnit(@QueryParam("name") String name) {
        return super.getEqualsSingle("name", name);
    }
}
