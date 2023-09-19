//package com.circron.resource;
//
//import com.circron.resource.table.AdminPriv;
//import com.sun.jersey.spi.resource.Singleton;
//
//import java.util.List;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.QueryParam;
//@Singleton
//@Path("adminpriv")
//public class AdminPrivResource extends AbstractModelResource<AdminPriv> {
//    public AdminPrivResource() { super(AdminPriv.class); }
//
//    @SuppressWarnings("unchecked")
//    @GET
//    @Path("/byuser")
//    public List<AdminPriv> getByUser(@QueryParam("user") Long userId) {
//        return super.getEquals("userId", userId);
//    }
//}
