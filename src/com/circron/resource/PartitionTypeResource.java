package com.circron.resource;

import com.circron.resource.table.PartitionType;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("partition_type")
public class PartitionTypeResource extends AbstractModelResource<PartitionType> {
    public PartitionTypeResource() { super(PartitionType.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/byname")
    public PartitionType getByPartition(@QueryParam("name") String name) {
        return super.getEqualsSingle("name", name);
    }
}
