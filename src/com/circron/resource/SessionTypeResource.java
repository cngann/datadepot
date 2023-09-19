package com.circron.resource;

import com.circron.resource.table.SessionType;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("session_type")
public class SessionTypeResource extends AbstractModelResource<SessionType> {
    public SessionTypeResource() { super(SessionType.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/byname")
    public SessionType getBySession(@QueryParam("name") String name) {
        return super.getEqualsSingle("name", name);
    }
}
