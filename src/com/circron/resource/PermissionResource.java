package com.circron.resource;

import com.circron.resource.table.Permission;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("permission")
public class PermissionResource extends AbstractModelResource<Permission> {
    public PermissionResource() { super(Permission.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/bypermission")
    public Permission getByPermission(@QueryParam("permission") String id) {
        return super.getEqualsSingle("id", id);
    }
}
