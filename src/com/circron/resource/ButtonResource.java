package com.circron.resource;

import com.circron.resource.table.Button;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("button")
public class ButtonResource extends AbstractModelResource<Button> {
    public ButtonResource() { super(Button.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/bybutton")
    public Button getByButton(@QueryParam("button") String id) {
        return super.getEqualsSingle("id", id);
    }
}
