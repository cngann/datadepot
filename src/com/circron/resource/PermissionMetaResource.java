package com.circron.resource;

import com.circron.resource.table.PermissionMeta;
import com.sun.jersey.api.core.ResourceContext;
import com.sun.jersey.spi.resource.Singleton;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

@Singleton
@Path("permission_meta")
public class PermissionMetaResource extends AbstractModelResource<PermissionMeta> {
    @Context
    private ResourceContext _resourceContext;

    public PermissionMetaResource() { super(PermissionMeta.class); }

    @GET
    @Path("/bypermissionid")
    public List<PermissionMeta> getByPermissionId(@QueryParam("permission") Long id) {
        return super.getEquals("permissionId", id);
    }
}
