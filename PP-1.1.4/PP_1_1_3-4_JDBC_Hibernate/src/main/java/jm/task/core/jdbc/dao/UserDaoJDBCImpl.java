package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String INSERT_USER = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?);";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?;";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS users;";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, age INT NOT NULL, PRIMARY KEY (id));";
    private static final String CLEAN_TABLE = "TRUNCATE TABLE users;";
    private static final String SELECT_USER = "SELECT * FROM users;";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void dropUsersTable() throws SQLException {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DROP_TABLE)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void removeUserById(long id) throws SQLException {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                users.add(new User(name, lastName, age));
            }
        } catch (SQLException e) {
            throw e;
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CLEAN_TABLE)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
}
