package Controller;

import DAO.GenericDaoImpl;
import DAO.IGenericDao;
import Model.EtakemonObject;
import Model.User;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Miguel Angel on 19/04/2017.
 */
public class EtakemonManagerImpl implements EtakemonManager {


    final static Logger logger = Logger.getLogger(EtakemonManagerImpl.class);
    private static final EtakemonManagerImpl etakemonManagerImplInstance = null;
    private HashMap<Integer, User> hasMapUsers = new HashMap<>();

    private EtakemonManagerImpl(){}

    public static EtakemonManagerImpl getInstance()
    {
        if(etakemonManagerImplInstance == null)
        {
            return new EtakemonManagerImpl();
        }
        return etakemonManagerImplInstance;
    }

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

    private final IGenericDao<User> serviceUser = new GenericDaoImpl<User>();
    private final IGenericDao<EtakemonObject> serviceEtakemonObject = new GenericDaoImpl<EtakemonObject>();


    public boolean createUser(String name, String surname, String username, String password, String email) {
        StringBuffer message = new StringBuffer();
        message.append("Try create user with paramenters: Name: "+name+" Surname: "+surname+" Username: "+username+" Password: "+password+" Email: "+email);

        User user = new User(name, surname, username, password, email);
        boolean result = serviceUser.add(user);

        if(!result)
        {
            return result;
        }

        message.append("Usuario created");
        message.append("\n");
        message.append("with params:") ;
        logMessage(user, message);
        return result;
    }

    public boolean updateUser(User newUser) {
        StringBuffer message = new StringBuffer();
        User user = serviceUser.getById(newUser, newUser.getId());

        if (user == null) {

            message.append("User doesn't exist");
            logMessage(newUser, message);
            return false;
        }

       boolean result = serviceUser.updateById(user, user.getId());
        if(!result)
        {
            message.append("User doesn't updated");

        }
        message.append("User updated");
        logMessage(newUser, message);
        return result;
    }

    public User getUser(int id) {
        StringBuffer message = new StringBuffer();
        User user = new User();
        user = serviceUser.getById(user, id);

        if(user.getUsername() == null || user.getUsername().isEmpty())
        {
            logger.info("User with id: "+id+" doesn't exist");
            return null;
        }

        List<EtakemonObject> listEtakemonObject = serviceEtakemonObject.getAll(new EtakemonObject());
        List<EtakemonObject> listToAdd = new ArrayList<>();
        for (EtakemonObject item: listEtakemonObject) {
            if(item.getId() == user.getId())
            {
                listToAdd.add(item);
            }
        }
        user.setListEtakemon(listToAdd);

        message.append("Usuario con id: "+id+" obtenido correctamente:");
        logMessage(user, message);
        return user;
    }

    public boolean addObject(User user, String etakemonName) {
        StringBuffer message = new StringBuffer();
        User userObtained = serviceUser.getById(user, user.getId());
        logger.info("Try addObect with name = "+etakemonName);

        if(user.getUsername() == null || user.getUsername().isEmpty())
        {
            message.append("User doesn't exist");
            logMessage(user, message);
            return false;
        }

        EtakemonObject etakemon = new EtakemonObject(user.getId(), etakemonName);
        boolean result = serviceEtakemonObject.add(etakemon);

        if(!result)
        {
            logger.info("Something wrong ocurred adding object");
            return false;
        }
        etakemon = serviceEtakemonObject.getById(etakemon, etakemon.getIdEtakemon());
        user.setEtakemonObject(etakemon);

        message.append("Etakemon "+etakemonName+" added");
        logMessage(etakemon, message);
        return true;
    }

    public List<User> getUserList() {
        StringBuffer message = new StringBuffer();
        logger.info("Try get all users");

        List<User> listUser = new ArrayList<>();
        listUser = serviceUser.getAll(new User());

        if(listUser.size()>0)
        {
            for (User user:listUser) {
                hasMapUsers.put(user.getId(), user);
                logMessage(user, message.append("User obtained"));
            }
        }
        return listUser;

    }

    public List<EtakemonObject> getEtakemonListByUser(User user) {
        StringBuffer message = new StringBuffer();
        if(user == null)
        {
            message.append("Error, incorrect user");
            logMessage(user, message);
            return null;
        }
        User userResponse = getUser(user.getId());
        List<EtakemonObject> list = userResponse.getListEtakemon();
        message.append("Lista etakemonObject Obtenida correctamente:");
        logMessage(user, message);
        return list;
    }


    private void logMessage(Object item, StringBuffer message) {

        try {
            message.append("\n");
            Class nameClass = item.getClass();
            String simpleNameClass = nameClass.getSimpleName();
            message.append(simpleNameClass);
            message.append(": ");
            message.append("\n");
            Field[] propertyClass = nameClass.getDeclaredFields();
            for (int i = 0; i < propertyClass.length; i++) {

                message.append(propertyClass[i].getName());
                message.append(" = ");
                propertyClass[i].setAccessible(true);
                message.append(propertyClass[i].get(item));
                message.append("\n ");
            }
            logger.info(message);
        } catch (Exception ex) {
            logger.error("Error log message", ex);
        }
    }



}
