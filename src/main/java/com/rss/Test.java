package com.rss;

import com.rss.model.Address;
import com.rss.model.Employee;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class Test {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Employee HelloWorld() {

        Address a = new Address();
        Employee e = new Employee();
        e.setAddress(a);
        e.setId(42);
        e.setName("loLzar");
        e.setPermanent(true);
        e.setPhoneNumbers(new long[]{0625252525, 062545654});
        e.setRole("LELOL");



        //String result = "HELLO!";
       // return Response.status(200).entity(result).build();
        return e;
    }

}