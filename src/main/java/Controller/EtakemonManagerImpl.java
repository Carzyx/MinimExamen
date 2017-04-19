package Controller;

import Model.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
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
            logger.info("Usuario Actualizado correctamente:");

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
        User user = listUser.get(id);
        logger.info("Usuario Obtenido correctamente:");
        return user;

    }

    public void addObject(User user, String etakemonName) {
        EtakemonObject etakemon = new EtakemonObject(etakemonIdCount, etakemonName);
        user.setEtakemonObject(etakemon);
        etakemonIdCount++;
        logger.info("Etakemon objeto añadido correctamente:");

    }

    public List<User> getUserList() {
       try {
           List<User> list = new ArrayList<User>();

           for (int i=1; i <= listUser.size(); i++ )
           {
               if(listUser.containsKey(i))
               {
                   User user = listUser.get(i);
                   list.add(user);
               }
           }
           return list;
       }
       catch (Exception ex)
       {
           logger.error("Error obteniendo lista de usuarios", ex);
           return null;
       }

    }

    public List<EtakemonObject> getEtakemonListByUser(User user) {
        List<EtakemonObject> list =  user.getListEtakemon();
        logger.info("Lista etakemonObject Obtenida correctamente:");
        return list;
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
