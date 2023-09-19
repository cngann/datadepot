package com.circron.resource;

import com.circron.resource.table.StreamMeta;
import com.sun.jersey.api.core.ResourceContext;
import com.sun.jersey.spi.resource.Singleton;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

@Singleton
@Path("stream_meta")
public class StreamMetaResource extends AbstractModelResource<StreamMeta> {
    @Context
    private ResourceContext _resourceContext;

    public StreamMetaResource() { super(StreamMeta.class); }

    @GET
    @Path("/bystreamid")
    public List<StreamMeta> getByStreamId(@QueryParam("stream") Long id) {
        return super.getEquals("streamId", id);
    }
}
