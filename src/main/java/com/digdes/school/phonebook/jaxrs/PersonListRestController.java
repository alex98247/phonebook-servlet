package com.digdes.school.phonebook.jaxrs;

import com.digdes.school.phonebook.model.Person;
import com.digdes.school.phonebook.service.PersonServiceImpl;
import com.wordnik.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("personList")
@Api(value = "personList", description = "Get person list")
public class PersonListRestController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "personList")
    public List<Person> get()  {
        return PersonServiceImpl.getInstance().list();
    }
}
