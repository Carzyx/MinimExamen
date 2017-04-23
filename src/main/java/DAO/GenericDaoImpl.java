package DAO;

import Model.User;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Miguel Angel on 04/04/2017.
 */
public class GenericDaoImpl<T> extends MySQLRepository<T> implements IGenericDao<T> {

    final static Logger logger=Logger.getLogger(GenericDaoImpl.class);

    public boolean add(T t){
        boolean result = false;
        try{
            StringBuffer query = new StringBuffer();

            Class nameClass = t.getClass();
            String simpleNameClass = nameClass.getSimpleName();

            query.append("INSERT INTO ");
            query.append(simpleNameClass.toLowerCase()+" (");

            Field[] propertyClass = nameClass.getDeclaredFields();
            int fixBug = simpleNameClass.toLowerCase().equals("user") ? 1 : 0;
            int length =propertyClass.length - fixBug;
            for(int i=1; i< length; i++)
            {
                query.append(propertyClass[i].getName().toLowerCase());

                if(i != length-1)
                {
                    query.append(", ");
                }
                if(i == length-1)
                {
                    query.append(") ");
                }
            }
            query.append("VALUES (");
            for(int i=1; i< length; i++)
            {
                query.append("?");

                if(i != length-1)
                {
                    query.append(", ");
                }
                if(i == length-1)
                {
                    query.append(") ");
                }
            }
            result = add(t, query.toString());
        }
        catch (Exception ex)
        {
            logger.error("Error in add, Exception:", ex);

        }

        return result;
    }


    public boolean updateById(T t, int id)  {

        //UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;
        boolean result = false;

        try{
            StringBuffer query = new StringBuffer();

            Class nameClass = t.getClass();
            String simpleNameClass = nameClass.getSimpleName();

            query.append(" UPDATE ");
            query.append(simpleNameClass.toLowerCase());
            query.append(" SET ");

            Field[] propertyClass = nameClass.getDeclaredFields();

            int fixBug = simpleNameClass.toLowerCase().equals("user") ? 1 : 0;
            int length =propertyClass.length - fixBug;
            for (int i = 1; i < length; i++) {

                query.append(propertyClass[i].getName());

                query.append("= ");
                query.append("'");
                propertyClass[i].setAccessible(true);
                query.append(propertyClass[i].get(t));
                query.append("'");

                if (i < length - 1) {
                    query.append(", ");
                }
            }
           query.append(whereIdCondition(id));


            result = update(t, query.toString());
        }
        catch (Exception ex)
        {
            logger.error("Error in updateById, Exception:", ex);
        }

        return result;
    }


    public T getById(T t, int id) {

        //SELECT * FROM table_name;
        try{
            StringBuffer query = new StringBuffer();

            Class nameClass = t.getClass();
            String simpleNameClass = nameClass.getSimpleName();

            query.append("SELECT * FROM ");
            query.append(simpleNameClass.toLowerCase());

            query.append(whereIdCondition(id));

            return select(t, query.toString());

        }
        catch (Exception ex)
        {
            logger.error("Error in getById, Exception:", ex);
            return null;
        }
    }


    public List<T> getAll(T t){
        //SELECT * FROM table_name;
        StringBuffer query = new StringBuffer();

        Class nameClass = t.getClass();
        String simpleNameClass = nameClass.getSimpleName();

        query.append("SELECT * FROM ");
        query.append(simpleNameClass.toLowerCase());

        System.out.println(query);
        return getAll(t, query.toString());

    }

    private String whereIdCondition(int id)
    {
        StringBuffer queryCondition = new StringBuffer();
        queryCondition.append(" WHERE id = ");
        queryCondition.append("'");
        queryCondition.append(Integer.toString(id));
        queryCondition.append("'");
        return queryCondition.toString();
    }







}