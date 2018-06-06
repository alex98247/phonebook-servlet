package com.digdes.school.phonebook.jaxrs;


import com.digdes.school.phonebook.model.Person;
import com.digdes.school.phonebook.service.PersonServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

@Path("person")
@Api(value = "person", description = "Get, Delete, Add person")
public class PersonRestController {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "GetPerson")
    public Person get(@PathParam("id") Long id)  {
        return PersonServiceImpl.getInstance().get(id);
    }

    @POST
    @Consumes("application/json")
    @ApiOperation(value = "PostPerson")
    public void add(Person person)  { PersonServiceImpl.getInstance().add(person);
    }

    @DELETE
    @Path("{id}")
    @ApiOperation(value = "DeletePerson")
    public void delete(@PathParam("id") Long id)  {
        PersonServiceImpl.getInstance().delete(id);
    }
}
