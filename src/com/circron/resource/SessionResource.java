package com.circron.resource;

import com.circron.resource.table.Session;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("session")
public class SessionResource extends AbstractModelResource<Session> {
    public SessionResource() { super(Session.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/bysession")
    public Session getBySession(@QueryParam("session") String id) {
        return super.getEqualsSingle("id", id);
    }
}
