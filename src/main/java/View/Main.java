package View;

import Controller.EtakemonManagerImpl;
import Model.User;

/**
 * Created by Miguel Angel on 19/04/2017.
 */
public class Main {
    public static void main(String[] args) {


        EtakemonManagerImpl eetakemonManager = new EtakemonManagerImpl ();

        eetakemonManager.createUser("miguelA", "angelA", "miguelAA", "miguel123", "miguelA@miguel.es");
        eetakemonManager.createUser("miguelB", "angelB", "miguelBB", "migue456", "miguelB@miguel.es");
        eetakemonManager.createUser("miguelC", "angelC", "miguelCC", "miguel789", "miguelC@miguel.es");

        User userTestA = eetakemonManager.getUser(1);
        User userTestB = eetakemonManager.getUser(2);
        User userTestC = eetakemonManager.getUser(3);



        System.out.println("done");






    }

}
