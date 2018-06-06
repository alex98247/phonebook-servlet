package com.digdes.school.phonebook.jaxrs;

import com.digdes.school.phonebook.model.Person;
import com.digdes.school.phonebook.service.PersonServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.util.List;

@Path("page")
@Api(value = "/page", description = "Converts dbf file to csv")
public class PagesRest {

    @GET
    @Path("personList")
    @ApiOperation(value = "personList")
    public void personList(@Context HttpServletRequest req,
                               @Context HttpServletResponse resp){
        try {
            List<Person> list = PersonServiceImpl.getInstance().list();
            req.setAttribute("personList", list);
            req.getRequestDispatcher("/WEB-INF/jsp/person_list.jsp").forward(req, resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
