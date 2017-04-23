package Rest;


import Controller.EtakemonManager;
import Controller.EtakemonManagerImpl;
import Model.EtakemonObject;
import Model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Miguel Angel on 19/04/2017.
 */
@Path("service")
public class RestService {
    private EtakemonManager service = EtakemonManagerImpl.getInstance();

    public RestService()
    {
        //service.LoadUsers();
    }


    @Path("createUser/{name}/{surname}/{username}/{password}/{email}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createUser(@PathParam("name") String name, @PathParam("surname") String surname, @PathParam("username") String username, @PathParam("password") String password, @PathParam("email") String email) {
        if(service.createUser(name, surname, username, password, email))
        {
            return Response.status(201).entity("User created OK").build();
        }
        return Response.status(200).entity("User created KO").build();
    }

    @Path("updateUser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUser(User updateUser) {
        if(service.updateUser(updateUser))
        {
            return Response.status(201).entity("User updated OK").build();
        }
        return Response.status(200).entity("User updated KO").build();
    }


    @Path("getUser/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") int id) {

        return service.getUser(id);
    }

    @Path("addObject/{id}/{etakemonName}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response addObject(@PathParam("id") int id, @PathParam("etakemonName") String etakemonName) {

        User user = service.getUser(id);
        if(user != null)
        {
            if(service.addObject(user, etakemonName))
            {
                return Response.status(200).entity("Object added, OK").build();
            }
            return Response.status(201).entity("Object not added, something wrong occurred, KO").build();
        }
        return Response.status(200).entity("User doesn't exist, object not added, KO").build();
    }

    @Path("getUserList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUserList() {
        return service.getUserList();
    }

    @Path("getEtakemonListByUser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<EtakemonObject> getEtakemonListByUser(User user) {
        return service.getEtakemonListByUser(user);
    }

}
