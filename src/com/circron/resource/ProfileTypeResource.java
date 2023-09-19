package com.circron.resource;

import com.circron.resource.table.ProfileType;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("profile_type")
public class ProfileTypeResource extends AbstractModelResource<ProfileType> {
    public ProfileTypeResource() { super(ProfileType.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/byname")
    public ProfileType getByProfile(@QueryParam("name") String name) {
        return super.getEqualsSingle("name", name);
    }
}
