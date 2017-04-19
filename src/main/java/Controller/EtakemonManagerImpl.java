package Controller;

import Model.User;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Miguel Angel on 19/04/2017.
 */
public class EtakemonManagerImpl implements EtakemonManager {
    private int userIdCount = 1;
    private int etakemonIdCount = 1;

    private HashMap<Integer, User> listUser = new HashMap<Integer, User>();
    final static Logger logger=Logger.getLogger(EtakemonManagerImpl.class);


    public void createUser(String name, String surname, String username, String password, String email) {

        User user = new User(userIdCount ,name, surname, username, password, email);
        listUser.put(user.getId(), user);
        userIdCount++;

        logger.info("Usuario Creado correctamente:");
        logger.info("Parametros solicitados:");
        logger.info("   Name: "+name);
        logger.info("   Surname: "+surname);
        logger.info("   Username: "+username);
        logger.info("   Password: "+password);
        logger.info("   Email: "+email);

    }



    public void updateUser(User newUser, int id)
    {
        if(listUser.containsKey(id))
        {
            User user = listUser.get(id);
            updateUser(user, newUser);
            listUser.put(user.getId(), user);
        }
        else
        {

        }
    }

    public User getUser(int id) {

        if(!listUser.containsKey(id))
        {
            return null;

        }
        return listUser.get(id);
    }

    public void addObject(User user, String etakemonName) {
        EtakemonObject etakemon = new EtakemonObject(etakemonIdCount, etakemonName);
        user.setEtakemonObject(etakemon);
        etakemonIdCount++;
    }

    public List<EtakemonObject> getEtakemonListByUser(User user) {
        return user.getListEtakemon();
    }


    private User updateUser(User user, User newUser)
    {
        user.setName(newUser.getName());
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        user.setEmail(newUser.getEmail());
        return user;
    }
}
