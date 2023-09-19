package com.circron.resource;

import com.circron.resource.table.SessionMeta;
import com.sun.jersey.api.core.ResourceContext;
import com.sun.jersey.spi.resource.Singleton;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

@Singleton
@Path("session_meta")
public class SessionMetaResource extends AbstractModelResource<SessionMeta> {
    @Context
    private ResourceContext _resourceContext;

    public SessionMetaResource() { super(SessionMeta.class); }

    @GET
    @Path("/bysessionid")
    public List<SessionMeta> getBySessionId(@QueryParam("session") Long id) {
        return super.getEquals("sessionId", id);
    }
}
