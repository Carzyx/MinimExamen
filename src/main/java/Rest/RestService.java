package Rest;


import Controller.EtakemonManager;
import Controller.EtakemonManagerImpl;
import Model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Miguel Angel on 19/04/2017.
 */
@Path("service")
public class RestService {
    private EtakemonManager service = new EtakemonManagerImpl();

    public RestService()
    {
        service.createUser("miguelA", "angelA", "miguelAA", "miguel123", "miguelA@miguel.es");
        service.createUser("miguelB", "angelB", "miguelBB", "migue456", "miguelB@miguel.es");
        service.createUser("miguelC", "angelC", "miguelCC", "miguel789", "miguelC@miguel.es");
    }




    @Path("createUser/{name}/{surname}{username}/{password}/{email}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String createUser(@PathParam("name") String name, @PathParam("surname") String surname, @PathParam("username") String username, @PathParam("password") String password, @PathParam("email") String email) {
        service.createUser(name, surname, username, password, email);
        return "Create user Done!";

    }


    @Path("getUser/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") int id) {

        return  service.getUser(id);
    }

    @Path("getUserList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUserList() {
        return service.getUserList();
    }
/*
    public User updateUser(User user, int id) {

    }



    void addObject(User user, String etakemonName) {

    }



    List<EtakemonObject> getEtakemonListByUser(User user) {

    }
*/
}
