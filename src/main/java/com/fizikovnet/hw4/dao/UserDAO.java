package com.fizikovnet.hw4.dao;

import com.fizikovnet.hw4.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO {

    private Connection connection;

    public UserDAO(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    public int create(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into users (username) values (?)");
            statement.setString(1, user.getUsername());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findById(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from users where id = ? limit 1");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getLong("id"), resultSet.getString("username"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from users");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(new User(resultSet.getLong("id"), resultSet.getString("username")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int update(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("update users set username = ? where id = ?");
            statement.setString(1, user.getUsername());
            statement.setLong(2, user.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("delete from users where id = ? and username = ?");
            statement.setLong(1, user.getId());
            statement.setString(2, user.getUsername());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
