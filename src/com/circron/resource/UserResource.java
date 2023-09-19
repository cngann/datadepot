package com.circron.resource;

import com.circron.resource.table.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Singleton
@Path("user")
public class UserResource extends AbstractModelResource<User> {
    public UserResource() { super(User.class); }

    @SuppressWarnings("unchecked")
    @GET
    @Path("/byuser")
    public User getByUser(@QueryParam("user") String loginId) {
        return super.getEqualsSingle("loginId", loginId);
    }

    @SuppressWarnings("unchecked")
    @POST
    @Path("/createuser")
    public void createUser(String userJsonString) throws JsonProcessingException {
//        return super.getEqualsSingle("loginId", loginId);

        System.out.println("attempting to add user " + userJsonString);
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(userJsonString, User.class);
        add(user);
    }
}
