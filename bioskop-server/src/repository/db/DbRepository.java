package repository.db;

import repository.Repository;
import repository.db.DbConnectionFactory;

public interface DbRepository<T>  extends Repository<T> {
    default public void connect() throws Exception{
        DbConnectionFactory.getInstance().getConnection();
    }

    default public void disconnect() throws Exception{
        DbConnectionFactory.getInstance().getConnection().close();
    }

    default public void commit() throws Exception{
        DbConnectionFactory.getInstance().getConnection().commit();
    }

    default public void rollback() throws Exception{
        DbConnectionFactory.getInstance().getConnection().rollback();
    }
}
