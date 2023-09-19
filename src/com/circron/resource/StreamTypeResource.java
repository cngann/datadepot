package com.circron.resource;

import com.circron.resource.table.StreamType;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("stream_type")
public class StreamTypeResource extends AbstractModelResource<StreamType> {
    public StreamTypeResource() { super(StreamType.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/byname")
    public StreamType getByStream(@QueryParam("name") String name) {
        return super.getEqualsSingle("name", name);
    }
}
