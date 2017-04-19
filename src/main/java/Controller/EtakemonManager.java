package Controller;

import Model.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Miguel Angel on 19/04/2017.
 */
public interface EtakemonManager {
    void createUser(String name, String surname, String username, String password, String email);
    void updateUser(User user, int id);
    User getUser(int id);
    void addObject(User user, String etakemonName);
    List<EtakemonObject> getEtakemonListByUser(User user);

}
