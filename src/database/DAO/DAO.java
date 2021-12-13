package database.DAO;

import java.sql.SQLException;
import java.util.List;

/**
 * DAO API
 * @param <T>
 */
public interface DAO<T> {
    /**
     * Saves new T in database
     * @param t without Id
     * @return saved T with set Id from database
     * @throws SQLException
     */
    T save(T t) throws SQLException;

    /**
     * Deletes t based on id of t
     * @param t
     * @throws SQLException
     */
    void delete(T t) throws SQLException;

    /**
     * Gets t based on id
     * @param id
     * @return t with given id
     * @throws SQLException
     */
    T get(int id) throws SQLException;

    /**
     * Gets t based on id
     * @param t with id
     * @return t with the same id
     * @throws SQLException
     */
    T get(T t) throws SQLException;

    /**
     * Gets all t objects from database
     * @return list of all t objects
     * @throws SQLException
     */
    List<T> getAll() throws SQLException;

    /**
     * Updates t in database with id of given t
     * @param t with id
     * @throws SQLException
     */
    void update(T t) throws SQLException;
}
