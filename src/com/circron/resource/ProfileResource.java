package com.circron.resource;

import com.circron.resource.table.Profile;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("profile")
public class ProfileResource extends AbstractModelResource<Profile> {
    public ProfileResource() { super(Profile.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/byprofile")
    public Profile getByProfile(@QueryParam("profile") String id) {
        return super.getEqualsSingle("id", id);
    }
}
