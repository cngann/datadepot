package com.circron.resource;

import com.circron.resource.table.Notification;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("notification")
public class NotificationResource extends AbstractModelResource<Notification> {
    public NotificationResource() { super(Notification.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/bynotification")
    public Notification getByNotification(@QueryParam("notification") String id) {
        return super.getEqualsSingle("id", id);
    }
}
