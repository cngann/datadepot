package com.circron.resource;

import com.circron.resource.table.PartitionMeta;
import com.sun.jersey.api.core.ResourceContext;
import com.sun.jersey.spi.resource.Singleton;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

@Singleton
@Path("partition_meta")
public class PartitionMetaResource extends AbstractModelResource<PartitionMeta> {
    @Context
    private ResourceContext _resourceContext;

    public PartitionMetaResource() { super(PartitionMeta.class); }

    @GET
    @Path("/bypartitionid")
    public List<PartitionMeta> getByPartitionId(@QueryParam("partition") Long id) {
        return super.getEquals("partitionId", id);
    }
}
