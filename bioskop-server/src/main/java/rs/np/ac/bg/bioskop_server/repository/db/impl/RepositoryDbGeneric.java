package rs.np.ac.bg.bioskop_server.repository.db.impl;

import domen.GenericEntity;
import rs.np.ac.bg.bioskop_server.repository.db.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RepositoryDbGeneric implements DbRepository<GenericEntity> {

    @Override
    public void add(GenericEntity param) throws Exception { ////////////////20190011//////////////////////////
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder();
            //String sql = "INSERT INTO " + param.getTableName() + param.getColumnNamesWithoutId() + " VALUES " + param.getInsertValues();
            sb.append("INSERT INTO ")
                    .append(param.getTableName())
                    .append(param.getColumnNamesWithoutId())
                    .append(" VALUES ").append(param.getInsertValues());
            String sql = sb.toString();
            System.out.println("Query: " + sql);
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rsKeys = statement.getGeneratedKeys();
            if (rsKeys.next()) {
                long id = rsKeys.getLong(1);
                System.out.println("Succesful insert!\nGenerated id: " + id);
                param.setId(id);
            }
            rsKeys.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<GenericEntity> getAll(GenericEntity param) throws Exception {
        try {
            List<GenericEntity> list = new ArrayList<>();
            String sql = "SELECT * FROM " + param.getTableName();

            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            System.out.println("Query: " + sql);
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                GenericEntity object = param.extractFromResultSet(resultSet);
                System.out.println("Adding object: " + object);
                list.add(object);
            }
            System.out.println("Objects fetched succesfully");
            statement.close();

            return list;
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public GenericEntity getById(GenericEntity param) throws Exception {
        try {
            String sql = "SELECT * FROM " + param.getTableName() + " WHERE " + param.getWhereCondition();

            Connection connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            System.out.println("Query: " + sql);
            ResultSet resultSet = statement.executeQuery(sql);

            if(resultSet.next()){
                GenericEntity object = param.extractFromResultSet(resultSet);
                System.out.println("Fetching object: " + object);
                statement.close();
                return object;
            } else {
                throw new SQLException("Nothing found in " + param.getTableName() + " with ID = " + param.getId());
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    public void edit(GenericEntity param) throws Exception {

        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            String sql = "UPDATE " + param.getTableName() + " SET " + param.getValues() + " WHERE " + param.getWhereCondition();
            System.out.println("Query: " + sql);

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Object updated succesfully");
            statement.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw e;
        }

    }

    @Override
    public void delete(GenericEntity param) throws Exception {
        try {

            Connection connection = DbConnectionFactory.getInstance().getConnection();
            String sql = "DELETE FROM " + param.getTableName() + " WHERE " + param.getWhereCondition() +";";

            System.out.println("Query: " + sql);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

            System.out.println("Deleted sucessfully");
            statement.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<GenericEntity> getAll() {
        throw new UnsupportedOperationException("Impossible to call generic getAll()");
    }


}
