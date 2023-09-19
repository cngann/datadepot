package com.circron.resource;

import com.circron.resource.table.KeyMeta;
import com.sun.jersey.api.core.ResourceContext;
import com.sun.jersey.spi.resource.Singleton;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

@Singleton
@Path("key_meta")
public class KeyMetaResource extends AbstractModelResource<KeyMeta> {
    @Context
    private ResourceContext _resourceContext;

    public KeyMetaResource() { super(KeyMeta.class); }

    @GET
    @Path("/bykeyid")
    public List<KeyMeta> getByKeyId(@QueryParam("key") Long id) {
        return super.getEquals("keyId", id);
    }
}
