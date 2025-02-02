package com.caresle.jodos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Model {
    protected static String table;
    protected Map<String, Object> attributes = new HashMap<>();

    public void set(String key, Object value) {
        attributes.put(key, value);
    }

    public Object get(String key) {
        return attributes.get(key);
    }

    public boolean save() throws SQLException {
        if (attributes.containsKey("id")) {
            return update();
        }

        return insert();
    }

    private boolean insert() throws SQLException {
        String columns = String.join(", ", attributes.keySet());
        String valuesPlaceholder = String.join(", ", Collections.nCopies(attributes.size(), "?"));

        String sql = "INSERT INTO " + table + " (" + columns + ") VALUES (" + valuesPlaceholder + ")";

        try (Connection connection = DB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setParameters(statement);

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();

                if (resultSet.next()) {
                    attributes.put("id", resultSet.getLong(1));
                }
            }
            return affectedRows > 0;
        }
    }

    private boolean update() throws SQLException {
        String setClause = String.join(" = ? ", attributes.keySet() + " = ? ");
        String sql = "UPDATE " + table + " SET " + setClause + " WHERE id = ?";

        try ( Connection connection = DB.getConnection()) { 
            PreparedStatement statement = connection.prepareStatement(sql);
            setParameters(statement);
            statement.setObject(attributes.size() + 1, attributes.get("id"));
            return statement.executeUpdate() > 0;
        }
    }

    public boolean delete() throws SQLException {
        String sql = "DELETE FROM " + table + " WHERE id = ?";

        try (Connection connection = DB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, attributes.get("id"));
            return statement.executeUpdate() > 0;
        }
    }

    public static <T extends Model> T findById(Class<T> modelClass, long id) {
        String sql = "SELECT * FROM " + table + " WHERE id = ?";

        try (Connection connection = DB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                try {
                    T instance = modelClass.getDeclaredConstructor().newInstance();
                    
                    ResultSetMetaData metaData = resultSet.getMetaData();
    
                    for (int i = 1; i <= metaData.getColumnCount(); i++) {
                        instance.set(metaData.getColumnName(i), resultSet.getObject(i));
                    }

                    return instance;
                } catch (Exception e) {
                    throw new RuntimeException("Error creating instance of " + modelClass.getName(), e);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL ERROR" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private void setParameters(PreparedStatement statement) throws SQLException {
        int index = 1;

        for (Object value: attributes.values()) {
            statement.setObject(index++, value);
        }
    }

    @Override
    public String toString() {
        return "Model{" +
                "attributes=" + attributes +
                '}';
    }
}
