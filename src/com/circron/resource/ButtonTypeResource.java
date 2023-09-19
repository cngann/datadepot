package com.circron.resource;

import com.circron.resource.table.ButtonType;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("button_type")
public class ButtonTypeResource extends AbstractModelResource<ButtonType> {
    public ButtonTypeResource() { super(ButtonType.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/byname")
    public ButtonType getByButton(@QueryParam("name") String name) {
        return super.getEqualsSingle("name", name);
    }
}
