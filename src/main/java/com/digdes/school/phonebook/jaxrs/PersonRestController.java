package com.digdes.school.phonebook.jaxrs;


import com.digdes.school.phonebook.model.Person;
import com.digdes.school.phonebook.service.PersonServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("person")
public class PersonRestController {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person get(@PathParam("id") Long id)  {
        return PersonServiceImpl.getInstance().get(id);
    }
}
