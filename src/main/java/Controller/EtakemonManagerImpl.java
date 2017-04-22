package Controller;

import Model.User;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
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
    final static Logger logger = Logger.getLogger(EtakemonManagerImpl.class);

    public void LoadUsers()
    {
        createUser("miguelA", "angelA", "miguelAA", "miguel123", "miguelA@miguel.es");
        User userA = getUser(1);
        addObject(userA, "Charmander");
        addObject(userA, "Pikachu");
        createUser("miguelB", "angelB", "miguelBB", "migue456", "miguelB@miguel.es");
        User userB = getUser(2);
        addObject(userB, "Squirtel");
        addObject(userB, "Bulbasur");
        createUser("miguelC", "angelC", "miguelCC", "miguel789", "miguelC@miguel.es");
        User userC = getUser(3);
        addObject(userC, "Articuno");
        addObject(userC, "Zapdos");
    }


    public boolean createUser(String name, String surname, String username, String password, String email) {

        User user = new User(userIdCount, name, surname, username, password, email);
        listUser.put(user.getId(), user);
        userIdCount++;

        StringBuffer message = new StringBuffer();
        message.append("Usuario creado");
        message.append("\n");
        message.append("con parametros Name: "+name+" Surname: "+surname+" Username: "+username+" Password: "+password+" Email: "+email);
        logMessage(user, message);

        return true;
    }

    public boolean updateUser(User newUser) {
        StringBuffer message = new StringBuffer();

        if (!listUser.containsKey(newUser.getId())) {

            message.append("User doesn't exist");
            logMessage(newUser, message);
            return false;
        }

        listUser.put(newUser.getId(), newUser);
        message.append("User updated");
        logMessage(newUser, message);
        return true;
    }

    public User getUser(int id) {
        StringBuffer message = new StringBuffer();

        if (!listUser.containsKey(id)) {
            logger.info("User with id: "+id+" doesn't exist");
            return null;
        }
        User user = listUser.get(id);
        message.append("Usuario con id: "+id+" obtenido correctamente:");
        logMessage(user, message);
        return user;
    }

    public boolean addObject(User user, String etakemonName) {
        StringBuffer message = new StringBuffer();
        if(!listUser.containsKey(user.getId()))
        {
            message.append("User doesn't exist");
            logMessage(user, message);
            return false;
        }

        EtakemonObject etakemon = new EtakemonObject(etakemonIdCount, etakemonName);
        user.setEtakemonObject(etakemon);
        etakemonIdCount++;

        updateUser(user);

        message.append("Etakemon "+etakemonName+" added and saved");
        logMessage(user, message);
        return true;
    }

    public List<User> getUserList() {
        try {
            List<User> list = new ArrayList<User>();

            for (int i = 1; i <= listUser.size(); i++) {
                if (listUser.containsKey(i)) {
                    User user = listUser.get(i);
                    list.add(user);
                }
            }
            return list;
        }
        catch (Exception ex) {
            logger.error("Error obteniendo lista de usuarios", ex);
            return null;
        }

    }

    public List<EtakemonObject> getEtakemonListByUser(User user) {
        StringBuffer message = new StringBuffer();
        if(user == null)
        {
            message.append("Error, incorrect user");
            logMessage(user, message);
            return null;
        }

        List<EtakemonObject> list = user.getListEtakemon();
        message.append("Lista etakemonObject Obtenida correctamente:");
        logMessage(user, message);
        return list;
    }


    private void logMessage(User user, StringBuffer message) {

        try {
            message.append("\n");
            Class nameClass = user.getClass();
            String simpleNameClass = nameClass.getSimpleName();
            message.append(simpleNameClass);
            message.append(": ");
            message.append("\n");
            Field[] propertyClass = nameClass.getDeclaredFields();
            for (int i = 0; i < propertyClass.length; i++) {

                message.append(propertyClass[i].getName());
                message.append(" = ");
                propertyClass[i].setAccessible(true);
                message.append(propertyClass[i].get(user));
                message.append("\n ");
            }
            logger.info(message);
        } catch (Exception ex) {
            logger.error("Error log message", ex);
        }
    }

}
