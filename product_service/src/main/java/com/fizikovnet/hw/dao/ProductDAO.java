package com.fizikovnet.hw.dao;

import com.fizikovnet.hw.entity.Product;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAO {

    private final Connection connection;

    public ProductDAO(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    public int create(Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into products (account_number, balance, type) values (?, ?, ?)");
            statement.setInt(1, product.getAccountNumber());
            statement.setDouble(2, product.getBalance());
            statement.setString(3, product.getType());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Product findById(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from products where id = ? limit 1"
            );
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createProductFromResultSet(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> findAll() {
        List<Product> result = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from products");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(createProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int update(Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "update products set account_number = ?, balance = ?, type = ? where id = ?"
            );
            statement.setInt(1, product.getAccountNumber());
            statement.setDouble(2, product.getBalance());
            statement.setString(3, product.getType());
            statement.setLong(4, product.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "delete from products where id = ?"
            );
            statement.setLong(1, product.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> findAllByUserId(Integer userId) {
        List<Product> result = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from products where user_id = ?"
            );
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(createProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Product createProductFromResultSet(ResultSet rs) throws SQLException {
        return new Product(
                rs.getLong("id"),
                rs.getInt("account_number"),
                rs.getDouble("balance"),
                rs.getString("type"));
    }
}
