package com.circron.resource;

import com.circron.resource.table.UserType;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("user_type")
public class UserTypeResource extends AbstractModelResource<UserType> {
    public UserTypeResource() { super(UserType.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/byname")
    public UserType getByUser(@QueryParam("name") String name) {
        return super.getEqualsSingle("name", name);
    }
}
