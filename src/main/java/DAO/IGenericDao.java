package DAO;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by Miguel Angel on 05/04/2017.
 */
public interface IGenericDao <T> {
    boolean add(T t);
    boolean updateById(T t, int id);
    T getById(T t, int id);
    List<T> getAll(T t);


}
