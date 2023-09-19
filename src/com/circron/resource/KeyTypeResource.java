package com.circron.resource;

import com.circron.resource.table.KeyType;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("key_type")
public class KeyTypeResource extends AbstractModelResource<KeyType> {
    public KeyTypeResource() { super(KeyType.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/byname")
    public KeyType getByKey(@QueryParam("name") String name) {
        return super.getEqualsSingle("name", name);
    }
}
