package Model;

import Controller.EtakemonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by histo on 06/03/2017.
 */
public class User {
    private int id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;

    private List<EtakemonObject> listEtakemon = new ArrayList<>();

    public User(){}

    public User(int id, String name, String surname, String username, String password, String email)
    {
        setId(id);
        setName(name);
        setSurname(surname);
        setUsername(username);
        setPassword(password);
        setEmail(email);
    }

    public int getId()
    {
        return this.id;
    }
    public void setId( int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return this.surname;
    }
    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getUsername()
    {
        return this.username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return this.password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return this.email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setEtakemonObject(EtakemonObject etakemonObject)
    {
        listEtakemon.add(etakemonObject);
    }
    public List<EtakemonObject> getListEtakemon()
    {
        return this.listEtakemon;
    }
    public void setListEtakemon(List<EtakemonObject> listEtakemon)
    {
        this.listEtakemon = listEtakemon;
    }



}
