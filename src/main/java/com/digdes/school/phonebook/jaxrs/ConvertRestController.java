package com.digdes.school.phonebook.jaxrs;

import com.digdes.school.phonebook.service.PersonServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import java.io.InputStream;
import javax.ws.rs.*;

@Path("convert")
@Api(value = "convert", description = "Converts dbf file to csv")
public class ConvertRestController {

    @POST
    @Consumes("multipart/mixed")
    @ApiOperation(value = "Convert")
    public void convert(@FormDataParam("file") InputStream inputStream,
                        @FormDataParam("file") FormDataContentDisposition fileDetail)  {
        PersonServiceImpl.getInstance().convert(fileDetail.getName(), inputStream);

    }
}
