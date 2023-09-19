package com.circron.resource;

import com.circron.resource.table.NotificationType;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("notification_type")
public class NotificationTypeResource extends AbstractModelResource<NotificationType> {
    public NotificationTypeResource() { super(NotificationType.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/byname")
    public NotificationType getByNotification(@QueryParam("name") String name) {
        return super.getEqualsSingle("name", name);
    }
}
