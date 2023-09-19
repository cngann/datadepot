package com.circron.resource;

import com.circron.resource.table.DeviceMeta;
import com.sun.jersey.api.core.ResourceContext;
import com.sun.jersey.spi.resource.Singleton;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

@Singleton
@Path("device_meta")
public class DeviceMetaResource extends AbstractModelResource<DeviceMeta> {
    @Context
    private ResourceContext _resourceContext;

    public DeviceMetaResource() { super(DeviceMeta.class); }

    @GET
    @Path("/bydeviceid")
    public List<DeviceMeta> getByDeviceId(@QueryParam("device") Long id) {
        return super.getEquals("deviceId", id);
    }
}
