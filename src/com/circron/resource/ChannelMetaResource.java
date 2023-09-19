package com.circron.resource;

import com.circron.resource.table.ChannelMeta;
import com.sun.jersey.api.core.ResourceContext;
import com.sun.jersey.spi.resource.Singleton;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

@Singleton
@Path("channel_meta")
public class ChannelMetaResource extends AbstractModelResource<ChannelMeta> {
    @Context
    private ResourceContext _resourceContext;

    public ChannelMetaResource() { super(ChannelMeta.class); }

    @GET
    @Path("/bychannelid")
    public List<ChannelMeta> getByChannelId(@QueryParam("channel") Long id) {
        return super.getEquals("channelId", id);
    }
}
