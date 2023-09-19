package com.circron.resource;

import com.circron.resource.table.Channel;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("channel")
public class ChannelResource extends AbstractModelResource<Channel> {
    public ChannelResource() { super(Channel.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/bychannel")
    public Channel getByChannel(@QueryParam("channel") String id) {
        return super.getEqualsSingle("id", id);
    }
}
