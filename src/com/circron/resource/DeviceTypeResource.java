package com.circron.resource;

import com.circron.resource.table.DeviceType;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("device_type")
public class DeviceTypeResource extends AbstractModelResource<DeviceType> {
    public DeviceTypeResource() { super(DeviceType.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/byname")
    public DeviceType getByDevice(@QueryParam("name") String name) {
        return super.getEqualsSingle("name", name);
    }
}
