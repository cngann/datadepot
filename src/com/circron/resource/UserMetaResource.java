package com.circron.resource;

import com.circron.resource.table.UserMeta;
import com.sun.jersey.api.core.ResourceContext;
import com.sun.jersey.spi.resource.Singleton;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

@Singleton
@Path("user_meta")
public class UserMetaResource extends AbstractModelResource<UserMeta> {
    @Context
    private ResourceContext _resourceContext;

    public UserMetaResource() { super(UserMeta.class); }

    @GET
    @Path("/byuserid")
    public List<UserMeta> getByUserId(@QueryParam("user") Long id) {
        return super.getEquals("userId", id);
    }
}
