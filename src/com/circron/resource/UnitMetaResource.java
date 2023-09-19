package com.circron.resource;

import com.circron.resource.table.UnitMeta;
import com.sun.jersey.api.core.ResourceContext;
import com.sun.jersey.spi.resource.Singleton;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

@Singleton
@Path("unit_meta")
public class UnitMetaResource extends AbstractModelResource<UnitMeta> {
    @Context
    private ResourceContext _resourceContext;

    public UnitMetaResource() { super(UnitMeta.class); }

    @GET
    @Path("/byunitid")
    public List<UnitMeta> getByUnitId(@QueryParam("unit") Long id) {
        return super.getEquals("unitId", id);
    }
}
