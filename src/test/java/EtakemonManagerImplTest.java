import Controller.EtakemonManagerImpl;
import Controller.EtakemonObject;
import Model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Miguel Angel on 19/04/2017.
 */
public class EtakemonManagerImplTest {
    static EtakemonManagerImpl defaulServiceEetakemon = new EtakemonManagerImpl();

    @BeforeClass
    public static void setUp()
    {
        defaulServiceEetakemon.createUser("miguelA", "angelA", "miguelAA", "miguel123", "miguelA@miguel.es");
        defaulServiceEetakemon.createUser("miguelB", "angelB", "miguelBB", "migue456", "miguelB@miguel.es");
        defaulServiceEetakemon.createUser("miguelC", "angelC", "miguelCC", "miguel789", "miguelC@miguel.es");

    }

    @After
    public void tearDown()
    {
        defaulServiceEetakemon = null;
    }

    @Test
    public void createUserandCheckUserNumbers()
    {
        defaulServiceEetakemon.createUser("miguelD", "angelD", "miguelDD", "miguel012", "miguelD@miguel.es");
        List<User> listUser = defaulServiceEetakemon.getUserList();
        assertEquals(listUser.size(), 4);
    }

    @Test
    public void addObjectAndCheckObjectNumber()
    {
        User user = defaulServiceEetakemon.getUser(1);
        defaulServiceEetakemon.addObject(user, "Pikachu");
        List<EtakemonObject> listEtakemonObject = defaulServiceEetakemon.getEtakemonListByUser(user);
        assertEquals(listEtakemonObject.size(), 1);
    }
}
