package com.circron.resource;

import com.circron.resource.table.PermissionType;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("permission_type")
public class PermissionTypeResource extends AbstractModelResource<PermissionType> {
    public PermissionTypeResource() { super(PermissionType.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/byname")
    public PermissionType getByPermission(@QueryParam("name") String name) {
        return super.getEqualsSingle("name", name);
    }
}
