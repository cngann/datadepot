package com.circron.resource;

import com.circron.resource.table.ButtonMeta;
import com.sun.jersey.api.core.ResourceContext;
import com.sun.jersey.spi.resource.Singleton;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

@Singleton
@Path("button_meta")
public class ButtonMetaResource extends AbstractModelResource<ButtonMeta> {
    @Context
    private ResourceContext _resourceContext;

    public ButtonMetaResource() { super(ButtonMeta.class); }

    @GET
    @Path("/bybuttonid")
    public List<ButtonMeta> getByButtonId(@QueryParam("button") Long id) {
        return super.getEquals("buttonId", id);
    }
}
