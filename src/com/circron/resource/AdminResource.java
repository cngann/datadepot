//package com.circron.resource;
//
//import com.circron.resource.table.Admin;
//import com.sun.jersey.spi.resource.Singleton;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.QueryParam;
//
//@Singleton
//@Path("admin")
//public class AdminResource extends AbstractModelResource<Admin> {
//    public AdminResource() { super(Admin.class); }
//
//    @SuppressWarnings("unchecked")
//    @GET
//    @Path("/byuser")
//    public Admin getByUser(@QueryParam("user") String username) {
//        return super.getEqualsSingle("userName", username);
//    }
//}
