package com.circron.resource;

import com.circron.resource.table.Device;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("device")
public class DeviceResource extends AbstractModelResource<Device> {
    public DeviceResource() { super(Device.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/bydevice")
    public Device getByDevice(@QueryParam("device") String macAddress) {
        return super.getEqualsSingle("macAddress", macAddress);
    }
}
