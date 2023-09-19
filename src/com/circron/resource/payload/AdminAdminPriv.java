//package com.circron.resource.payload;
//
//import com.circron.resource.AdminPrivResource;
//import com.circron.resource.AdminResource;
//import com.circron.resource.table.Admin;
//import com.circron.resource.table.AdminPriv;
//import com.sun.jersey.api.core.ResourceContext;
//import com.sun.jersey.spi.resource.Singleton;
//
//import java.util.List;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.QueryParam;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MediaType;
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlElementWrapper;
//import javax.xml.bind.annotation.XmlRootElement;
//
//@Path("adminadminprivpayload")
//@Singleton
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//public class AdminAdminPriv {
//    @Context
//    private ResourceContext _resourceContext;
//
//    public AdminAdminPriv() { }
//    private Admin getAdmin(String id) {
//        return _resourceContext.getResource(AdminResource.class).getByUser(id);
//    }
//
//    private List<AdminPriv> getAdminPriv(Long id) {
//        return _resourceContext.getResource(AdminPrivResource.class).getByUser(id);
//    }
//
//    @GET
//    public AdminAdminPrivPayload getAdminAdminPrivPayload(@QueryParam("id") String userId) {
//        final AdminAdminPrivPayload adminAdminPrivPayload = new AdminAdminPrivPayload();
//        adminAdminPrivPayload.admin = getAdmin(userId);
//        adminAdminPrivPayload.adminPrivs = getAdminPriv(adminAdminPrivPayload.admin.getId());
//        return adminAdminPrivPayload;
//    }
//
//    @XmlRootElement
//    public static class AdminAdminPrivPayload {
//        public AdminAdminPrivPayload() { };
//        @XmlElement(name="admin")
//        private Admin admin;
//        @XmlElementWrapper(name="admin")
//        private List<AdminPriv> adminPrivs;
//    }
//}
