package com.circron.resource;

import com.circron.resource.table.ChannelType;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("channel_type")
public class ChannelTypeResource extends AbstractModelResource<ChannelType> {
    public ChannelTypeResource() { super(ChannelType.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/byname")
    public ChannelType getByChannel(@QueryParam("name") String name) {
        return super.getEqualsSingle("name", name);
    }
}
