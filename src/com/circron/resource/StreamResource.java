package com.circron.resource;

import com.circron.resource.table.Stream;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("stream")
public class StreamResource extends AbstractModelResource<Stream> {
    public StreamResource() { super(Stream.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/bystream")
    public Stream getByStream(@QueryParam("stream") String id) {
        return super.getEqualsSingle("id", id);
    }
}
