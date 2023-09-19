package com.circron.resource;

import com.circron.resource.table.ProfileMeta;
import com.sun.jersey.api.core.ResourceContext;
import com.sun.jersey.spi.resource.Singleton;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

@Singleton
@Path("profile_meta")
public class ProfileMetaResource extends AbstractModelResource<ProfileMeta> {
    @Context
    private ResourceContext _resourceContext;

    public ProfileMetaResource() { super(ProfileMeta.class); }

    @GET
    @Path("/byprofileid")
    public List<ProfileMeta> getByProfileId(@QueryParam("profile") Long id) {
        return super.getEquals("profileId", id);
    }
}
