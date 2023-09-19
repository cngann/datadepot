package com.circron.resource;

import com.circron.resource.table.NotificationMeta;
import com.sun.jersey.api.core.ResourceContext;
import com.sun.jersey.spi.resource.Singleton;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

@Singleton
@Path("notification_meta")
public class NotificationMetaResource extends AbstractModelResource<NotificationMeta> {
    @Context
    private ResourceContext _resourceContext;

    public NotificationMetaResource() { super(NotificationMeta.class); }

    @GET
    @Path("/bynotificationid")
    public List<NotificationMeta> getByNotificationId(@QueryParam("notification") Long id) {
        return super.getEquals("notificationId", id);
    }
}
