package com.circron.resource;

import com.circron.resource.table.Key;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("key")
public class KeyResource extends AbstractModelResource<Key> {
    public KeyResource() { super(Key.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/bykey")
    public Key getByKey(@QueryParam("key") String id) {
        return super.getEqualsSingle("id", id);
    }
}
