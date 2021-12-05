package Database.DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    void save(T t) throws SQLException;

    void delete(T t) throws SQLException;

    T get(T t) throws SQLException;

    T getByID(int id) throws SQLException;

    List<T> getAll() throws SQLException;

    void update(T t) throws SQLException;
}
