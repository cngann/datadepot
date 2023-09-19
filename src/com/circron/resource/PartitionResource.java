package com.circron.resource;

import com.circron.resource.table.Partition;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("partition")
public class PartitionResource extends AbstractModelResource<Partition> {
    public PartitionResource() { super(Partition.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/bypartition")
    public Partition getByPartition(@QueryParam("partition") String id) {
        return super.getEqualsSingle("id", id);
    }
}
