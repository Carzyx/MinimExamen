package Controller;

/**
 * Created by Miguel Angel on 19/04/2017.
 */
public class EtakemonObject {
    private int id;
    private String nameEtakemon;

    public EtakemonObject(){}
    public EtakemonObject(int id, String name)
    {
        this.id = id;
        this.nameEtakemon = name;
    }

    public int getId(int id)
    {
        return this.id;
    }

    public String getNameEtakemon()
    {
        return this.nameEtakemon;
    }
    public void setNameEtakemon(String name)
    {
        this.nameEtakemon = name;
    }
}
