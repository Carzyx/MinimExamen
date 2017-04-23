package Model;

/**
 * Created by Miguel Angel on 19/04/2017.
 */
public class EtakemonObject {
    private int idEtakemon;
    private int id;
    private String nameEtakemon;

    public EtakemonObject(){}
    public EtakemonObject(int id, String name)
    {
        setId(id);
        setNameEtakemon(name);
    }

    public int getId()
    {
        return this.id;
    }
    public void setId(int id){this.id = id;}

    public String getNameEtakemon()
    {
        return this.nameEtakemon;
    }
    public void setNameEtakemon(String name)
    {
        this.nameEtakemon = name;
    }
    public int getIdEtakemon()
    {
        return this.idEtakemon;
    }
    public void setIdEtakemon(int idEtakemon){this.idEtakemon = idEtakemon;}
}
