package Controller;

import Model.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Miguel Angel on 19/04/2017.
 */
public interface EtakemonManager {
    boolean createUser(String name, String surname, String username, String password, String email);
    boolean updateUser(User user);
    User getUser(int id);
    boolean addObject(User user, String etakemonName);
    List<User> getUserList ();
    List<EtakemonObject> getEtakemonListByUser(User user);
    void LoadUsers();

}
