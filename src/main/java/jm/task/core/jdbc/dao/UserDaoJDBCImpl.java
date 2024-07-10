package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try (PreparedStatement pstm = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), lastname VARCHAR(45), age INT)")) {
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void dropUsersTable() {
        try (PreparedStatement pstm = connection.prepareStatement("DROP TABLE IF EXISTS users")) {
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastname, byte age) {
        try (PreparedStatement pstm = connection.prepareStatement("INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)")) {
            pstm.setString(1, name);
            pstm.setString(2, lastname);
            pstm.setByte(3, age);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (PreparedStatement pstm = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            pstm.setLong(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement pstm = connection.prepareStatement("SELECT * FROM users")) {
            try (ResultSet resultSet = pstm.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User(resultSet.getString("name"),
                            resultSet.getString("lastname"), resultSet.getByte("age"));
                    user.setId(resultSet.getLong("id"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (PreparedStatement pstm = connection.prepareStatement("TRUNCATE TABLE users")) {
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}